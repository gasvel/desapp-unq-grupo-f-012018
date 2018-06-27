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
import model.User;
import service.PostService;
import service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/META-INF/spring-persistence-context.xml","classpath*:/META-INF/spring-service-context.xml"})
@TransactionConfiguration(transactionManager="persistence.transactionManager",defaultRollback=true)
@Transactional
public class PostServiceTest {

	
	@Autowired
	private PostService postServ;
	
	@Autowired
	private UserService userServ;
	
	
	@Before
	public void setUp(){
		this.userServ.setTestMode(true);
	}
	

	@Test
	public void saveANewValidPostAndSinceDBHas4PostNowShouldHave5() {
		Post newPost = Build.aPost().buildValidPost().build();
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		newPost.setCreator(user);
		this.postServ.save(newPost);
		user.setName("ansofjans");
		this.userServ.update(user);
		assertEquals(5,this.postServ.retriveAll().size());
	}
	
	@Test
	public void addAReservation(){
		Post newPost = Build.aPost().buildValidPost().build();
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		newPost.setCreator(user);
		this.postServ.save(newPost);
		user.setName("ansofjans");
		this.userServ.update(user);
		newPost.addNewRent(Build.aRent().build());
		this.postServ.update(newPost);
		assertEquals(this.postServ.getById(newPost.getId()).getRents().size() , 1);

		
	}

}
