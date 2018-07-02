package services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import model.Build;
import model.Post;
import model.Rent;
import model.Reservation;
import model.User;
import service.PostService;
import service.RentService;
import service.ReserService;
import service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/META-INF/spring-persistence-context.xml","classpath*:/META-INF/spring-service-context.xml"})
@TransactionConfiguration(transactionManager="persistence.transactionManager",defaultRollback=true)
@Transactional
public class RentServiceTest {

	@Autowired
	private ReserService reservService;
	@Autowired
	private RentService rentService;
	@Autowired
	private PostService postServ;
	
	@Autowired
	private UserService userServ;
	
	@Before
	public void setUp(){
		this.userServ.setTestMode(true);
		this.rentService.setTestMode(true);
		this.reservService.setTestMode(true);
	}

	@Test
	public void saveANewValidRentAndSinceDBHas0ReservationsNowShouldHave1() {
		Rent newRent= Build.aRent().buildValidRent().build();
		Reservation newReservation = Build.aReservation().buildValidReservation().build();
		Post post = Build.aPost().buildValidPost().build();
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		user.setName("Robert");
		this.userServ.update(user);
		post.setCreator(user);
		User client = Build.anUser().valid().build();
		client.setEmail("mailfalso@gmail.com");
		this.postServ.save(post);
		this.userServ.save(client);
		newReservation.setClient(client);
		newReservation.setPost(post);
		this.reservService.saveWithPost(newReservation, post);		
		newRent.setPost(post);		
		
		this.rentService.save(newRent);
		assertEquals(1, this.rentService.retriveAll().size());
	}

	@Test
	public void shouldBeAbleToCreateARentFromAReservation(){
		Reservation newReservation = Build.aReservation().buildValidReservation().build();
		Post post = Build.aPost().buildValidPost().build();
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		user.setName("Robert");
		this.userServ.update(user);
		post.setCreator(user);
		User client = Build.anUser().valid().build();
		client.setEmail("mailfalso@gmail.com");
		this.postServ.save(post);
		this.userServ.save(client);
		newReservation.setClient(client);
		newReservation.setPost(post);
		this.reservService.saveWithPost(newReservation, post);		
		
		this.rentService.newRent(newReservation);
		assertEquals(1, this.rentService.retriveAll().size());
	}
	
	@Test
	public void shouldBeAbleToDeleteARent(){
		Rent newRent= Build.aRent().buildValidRent().build();
		Reservation newReservation = Build.aReservation().buildValidReservation().build();
		Post post = Build.aPost().buildValidPost().build();
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		user.setName("Robert");
		this.userServ.update(user);
		post.setCreator(user);
		User client = Build.anUser().valid().build();
		client.setEmail("mailfalso@gmail.com");
		this.postServ.save(post);
		this.userServ.save(client);
		newReservation.setClient(client);
		newReservation.setPost(post);
		this.reservService.saveWithPost(newReservation, post);		
		newRent.setPost(post);		
		
		this.rentService.save(newRent);
		this.rentService.delete(newRent);
		assertEquals(0, this.rentService.retriveAll().size());
	}
}
