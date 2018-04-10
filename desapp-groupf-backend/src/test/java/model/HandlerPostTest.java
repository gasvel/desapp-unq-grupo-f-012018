package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HandlerPostTest {
	public HandlerPost handler;
	public User owner;
	
	@Before
	public void setUp() throws Exception {
		handler = new HandlerPost();
		owner = Build.anUser().build();
	}
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidTypeOfVehicleItShouldThrowIllegalArgumentException() {		
		Post post= Build.aPost().buildValidPost().build();
		post.setTypeVehicle(null);
		handler.newPost(post, owner);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidCapacityItShouldThrowIllegalArgumentException() {		
		Post post= Build.aPost().buildValidPost().build();
		post.setCapacity(-2);
		handler.newPost(post, owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidLocationItShouldThrowIllegalArgumentException() {		
		Post post= Build.aPost().buildValidPost().build();
		post.setLocation("");
		handler.newPost(post, owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidDescriptionItShouldThrowIllegalArgumentException() {		
		Post post= Build.aPost().buildValidPost().build();
		post.setTypeVehicle(null);
		handler.newPost(post, owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidPhoneItShouldThrowIllegalArgumentException() {		
		Post post= Build.aPost().buildValidPost().build();
		post.setPhoneNumber("");
		handler.newPost(post, owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidAvailabilityItShouldThrowIllegalArgumentException() {		
		Post post= Build.aPost().buildValidPost().build();
		post.setTypeVehicle(null);
		handler.newPost(post, owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidPhotoItShouldThrowIllegalArgumentException() {		
		Post post= Build.aPost().buildValidPost().build();
		post.setPhoto(null);
		handler.newPost(post, owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidPriceItShouldThrowIllegalArgumentException() {		
		Post post= Build.aPost().buildValidPost().build();
		post.setPriceDay(-2);
		handler.newPost(post, owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidAddressToDropItShouldThrowIllegalArgumentException() {		
		Post post= Build.aPost().buildValidPost().build();
		post.setAddressToDrop("");
		handler.newPost(post, owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidAddressToPickUpItShouldThrowIllegalArgumentException() {		
		Post post= Build.aPost().buildValidPost().build();
		post.setAddressToPickUp("");
		handler.newPost(post, owner);
	}

	public void ifAnPostIsCreatedWithValidsArgumentsTheOwnerShouldBeANewPost() {		
		Post post= Build.aPost().buildValidPost().build();
		handler.newPost(post, owner);	
		assertEquals(1, owner.getPosts().size());
	}
}
