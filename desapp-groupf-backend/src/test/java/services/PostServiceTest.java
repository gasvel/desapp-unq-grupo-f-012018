package services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


import model.Build;
import model.Post;
import service.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/META-INF/spring-persistence-context.xml","classpath*:/META-INF/spring-service-context.xml"})
@TransactionConfiguration(transactionManager="persistence.transactionManager",defaultRollback=true)
@Transactional
public class PostServiceTest {

	
	@Autowired
	PostService postServ;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void saveANewValidPostAndSinceDBHas4PostNowShouldHave5() {
		Post newPost = Build.aPost().buildValidPost().build();
		newPost.setCreator(Build.anUser().valid().build());
		this.postServ.save(newPost);
		assertEquals(5,this.postServ.retriveAll().size());
	}

}
