package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HandlerReserRentTest {
	HandlerReserRent handler;
	Post post;
	User client;
	User owner;
	Rent rent;

	@Before
	public void setUp() throws Exception {
		handler = new HandlerReserRent();
		post = Build.aPost().build();
		client = Build.anUser().withCuil("1").build();
		owner = Build.anUser().withCuil("2").withPost(post).build();
		rent = Build.aRent().withUser(this.client).build();
	}

	@After
	public void tearDown() throws Exception {
		handler = new HandlerReserRent();
		post = Build.aPost().build();
		client = Build.anUser().withCuil("1").build();
		owner = Build.anUser().withCuil("2").withPost(post).build();
		rent = Build.aRent().withUser(this.client).build();
	}

	@Test
	public void ifTheClientOfTheRentConfirmsPickUpAndTheOwnerDoesntConfirmsInTheTimeGived_itShouldChangeTheRentStateToCancelled() throws InterruptedException {
		rent.PICK_UP_TIME = TimeUnit.SECONDS.toMillis(1);
		this.handler.confirmVehiclePickUp(rent,post, this.client);
		Thread.sleep(TimeUnit.SECONDS.toMillis(3));
		assertEquals(rent.getState(), Rent_State.Cancelled);
	}
	
	@Test
	public void ifTheOwnerOfTheRentConfirmsPickUpAndTheClientDoesntConfirmsInTheTimeGived_itShouldChangeTheRentStateToPickUpConfirmed() throws InterruptedException {
		rent.PICK_UP_TIME = TimeUnit.SECONDS.toMillis(1);
		this.handler.confirmVehiclePickUp(rent,post, this.owner);
		Thread.sleep(TimeUnit.SECONDS.toMillis(3));
		assertEquals(rent.getState(), Rent_State.PickUpConfirmed);
	}
	
	@Test
	public void ifTheClientOfTheRentConfirmsPickUpAndTheOwnerAlsoConfirmsItBeforeExpirationTime_itShouldChangeTheRentStateToConfirmed() {
		this.handler.confirmVehiclePickUp(rent,post, this.client);
		this.handler.confirmVehiclePickUp(rent,post, owner);
		assertEquals(rent.getState(), Rent_State.PickUpConfirmed);
	}
	
	@Test
	public void ifTheClientOfTheRentConfirmsPickUp_itShouldChangeTheRentStateToClientConfirmedPickUp() throws InterruptedException {
		this.handler.confirmVehiclePickUp(rent,post, client);
		assertEquals(rent.getState(), Rent_State.ClientConfirmedPickUp);
	}
	
	@Test
	public void ifTheOwnerOfTheRentConfirmsPickUp_itShouldChangeTheRentStateToOwnerConfirmedPickUp() throws InterruptedException {
		this.handler.confirmVehiclePickUp(rent,post, owner);
		assertEquals(rent.getState(), Rent_State.OwnerConfirmedPickUp);
	}
	
	@Test
	public void ifAReservationIsCreatedInAPostWithNoReservationsOrRents_thePostShouldHaveOneReservation() {
		this.handler.newReservation(new GregorianCalendar(2018,6,5).getTime(), new GregorianCalendar(2018,6,7).getTime(), this.client, this.post);
		assertEquals(this.post.getReservations().size(),1);
	}
	
	@Test
	public void ifAReservationIsCreatedAndConfirmedInAPostWithNoReservationsOrRents_thePostShouldHaveNoReservationsAndOneRent() {
		this.post.setPriceDay(40);
		this.post.setPriceHour(5);
		this.handler.newReservation(new GregorianCalendar(2018,6,5).getTime(), new GregorianCalendar(2018,6,7).getTime(), this.client, this.post);
		this.handler.confirmReservation(this.post, this.client,this.post.getReservations().get(0) );
		assertEquals(this.post.getReservations().size(),0);
		assertEquals(this.post.getRents().size(),1);
	}
	
	@Test
	public void ifAReservationIsCreatedAndConfirmedWithADurationOfTwoDaysAndTheCostOfEachDayIs40_theRentCostShouldBe80() {
		this.post.setPriceDay(40);
		this.post.setPriceHour(5);
		this.handler.newReservation(new GregorianCalendar(2018,6,5).getTime(), new GregorianCalendar(2018,6,7).getTime(), this.client, this.post);
		this.handler.confirmReservation(this.post, this.client,this.post.getReservations().get(0) );
		assertTrue(this.post.getRents().get(0).getCost()==80f);
	}
	
	
	
	@Test
	public void ifClientAndOwnerOfTheRentConfirmsVehicleReturned_itShouldChangeTheRentStateToRentDone() {
		this.rent.setState(Rent_State.PickUpConfirmed);
		this.handler.confirmVehicleReturns(this.rent, this.post, client, owner, Build.aScore().build());
		this.handler.confirmVehicleReturns(this.rent, this.post, owner,client, Build.aScore().build());
		assertEquals(this.rent.getState(), Rent_State.RentDone);
		
	}
	
	@Test
	public void ifTheClientOfTheRentConfirmsVehicleReturned_itShouldChangeTheRentStateToClientConfirmedReturn() throws InterruptedException {
		this.rent.setState(Rent_State.PickUpConfirmed);
		this.handler.confirmVehicleReturns(this.rent, this.post, client, owner, Build.aScore().build());
		assertEquals(rent.getState(), Rent_State.ClientConfirmedReturn);
	}
	
	@Test
	public void ifTheOwnerOfTheRentConfirmsVehicle_itShouldChangeTheRentStateToOwnerConfirmedReturn() throws InterruptedException {
		this.rent.setState(Rent_State.PickUpConfirmed);
		this.handler.confirmVehicleReturns(this.rent, this.post, owner,client, Build.aScore().build());
		assertEquals(rent.getState(), Rent_State.OwnerConfirmedReturn);
	}


}
