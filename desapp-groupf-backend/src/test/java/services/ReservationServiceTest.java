package services;

import static org.junit.Assert.*;

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
import model.Reservation;
import model.User;
import service.PostService;
import service.ReserService;
import service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/META-INF/spring-persistence-context.xml","classpath*:/META-INF/spring-service-context.xml"})
@TransactionConfiguration(transactionManager="persistence.transactionManager",defaultRollback=true)
@Transactional
public class ReservationServiceTest {
	
	@Autowired
	ReserService reservService;
	
	@Autowired
	PostService postServ;
	
	@Autowired
	UserService userServ;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void saveANewValidReservationAndSinceDBHas0ReservationsNowShouldHave1() {
		Reservation newReservation = Build.aReservation().buildValidReservation().build();
		Post post = Build.aPost().buildValidPost().build();
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		user.setName("Robert");
		this.userServ.update(user);
		post.setCreator(user);
		User client = Build.anUser().valid().build();
		this.postServ.save(post);
		this.userServ.save(client);
		newReservation.setClient(client);
		newReservation.setPost(post);
		this.reservService.saveWithPost(newReservation, post);
		assertEquals(1,this.reservService.retriveAll().size());
	}

}
