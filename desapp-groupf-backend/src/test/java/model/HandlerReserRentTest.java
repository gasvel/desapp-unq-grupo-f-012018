package model;

import static org.junit.Assert.*;

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


}
