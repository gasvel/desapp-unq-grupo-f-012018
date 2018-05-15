package services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import model.Build;
import model.Post;
import service.PostService;

public class PostServiceTest {

	
	@Autowired
	PostService postServ;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void saveANewValidPostAndSinceDBHas4PostNowShouldHave5() {
		Post newPost = Build.aPost().buildValidPost().build();
		this.postServ.save(newPost);
		assertEquals(5,this.postServ.retriveAll().size());
	}

}
