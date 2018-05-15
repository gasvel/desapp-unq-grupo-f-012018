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
	UserService userServ;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void ifAValidUserIsSavedInADBWith3UseresTheListOfUsersInDBShouldHaveSize4() {
		User user = Build.anUser().valid().build();
		this.userServ.save(user);
		assertEquals(4,this.userServ.retriveAll().size());
		assertEquals(user.getCuil(),this.userServ.getById(user.getId()).getCuil());
		
	}

}
