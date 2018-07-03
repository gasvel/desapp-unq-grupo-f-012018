package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import model.Build;
import model.User;
import service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/META-INF/spring-persistence-context.xml","classpath*:/META-INF/spring-service-context.xml"})
@TransactionConfiguration(transactionManager="persistence.transactionManager",defaultRollback=true)
@Transactional
public class UserServiceTest {
	
	/* The DB has already 3 users loaded from the script import.sql
	 */
	
	@Autowired
	private UserService userServ;
	
	
	@Before
	public void setUp(){
		this.userServ.setTestMode(true);
	}
	


	@Test
	public void ifAValidUserIsSavedInADBWith3UseresTheListOfUsersInDBShouldHaveSize4() {
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		assertEquals(5,this.userServ.retriveAll().size());
		assertEquals(user.getCuil(),this.userServ.getById(user.getId()).getCuil());
		
	}
	
	@Test
	public void ifAValidUserIsSavedAndAdds200CreditsItShouldHave200Credits() throws Exception{
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		this.userServ.addCredits(user.getId(), 200);
		assert(200 == this.userServ.getById(user.getId()).getCredits());

	}
	
	@Test
	public void ifAValidUserIsSavedAndAddsANegativeAmountOfCreditsItShouldThrowAnException(){
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		try {
			this.userServ.addCredits(user.getId(), -200);
			fail();
		}
		catch (Exception e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
		}

	}
	
	@Test
	public void ifAValidUserIsSavedAndAdds200CreditsAndExtract100CreditsItShouldHave100Credits() throws Exception{
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		this.userServ.addCredits(user.getId(), 200);
		this.userServ.removeCredits(user.getId(), 100);
		assert(100 == this.userServ.getById(user.getId()).getCredits());

	}
	
	@Test
	public void ifAValidUserIsSavedAndExtract100CreditsWhithoutHavingThatAmountItShouldThrowAnException(){
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		try {
			this.userServ.removeCredits(user.getId(), 100);
			fail();
		}
		catch (Exception e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
		}

	}
	
	@Test
	public void idAValidUserIsSaveAndRemovedTheListOfUserInDBShouldHave3OfLength(){
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		this.userServ.delete(user);
		assertEquals(4,this.userServ.retriveAll().size());
	}
	
	@Test
	public void ifAValidUserIsSavedEditedAndUpdatedTheNameOfTheUserShouldBeTheSameInDB() {
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		user.setName("Carlos Osorio");
		this.userServ.update(user);
		assertEquals(user.getName(),this.userServ.getById(user.getId()).getName());
		
	}
	
	

}
