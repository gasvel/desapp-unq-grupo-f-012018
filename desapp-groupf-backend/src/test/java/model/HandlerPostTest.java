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
	public void ifAPostIsCreatedWithInvalidTypeOfVehicle_itShouldThrowIllegalArgumentException() {		
		handler.newPost(null, 4, "aLocation", "aDescription", 
				"11111111", "availability", "photo", 100, 500, 
				"addressToDrop", "addressToPickUp", owner);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidCapacity_itShouldThrowIllegalArgumentException() {		
		handler.newPost(TypeVehicle.Auto, -4, "aLocation", "aDescription", 
				"11111111", "availability", "photo", 100, 500, 
				"addressToDrop", "addressToPickUp", owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidLocation_itShouldThrowIllegalArgumentException() {		
		handler.newPost(TypeVehicle.Auto, 4, "", "aDescription", 
				"11111111", "availability", "photo", 100, 500, 
				"addressToDrop", "addressToPickUp", owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidDescription_itShouldThrowIllegalArgumentException() {		
		handler.newPost(TypeVehicle.Auto, 4, "aLocation", "", 
				"11111111", "availability", "photo", 100, 500, 
				"addressToDrop", "addressToPickUp", owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidPhone_itShouldThrowIllegalArgumentException() {		
		handler.newPost(TypeVehicle.Auto, 4, "aLocation", "aDescription", 
				null, "availability", "photo", 100, 500, 
				"addressToDrop", "addressToPickUp", owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidAvailability_itShouldThrowIllegalArgumentException() {		
		handler.newPost(TypeVehicle.Auto, 4, "aLocation", "aDescription", 
				"11111111", "", "photo", 100, 500, 
				"addressToDrop", "addressToPickUp", owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidPhoto_itShouldThrowIllegalArgumentException() {		
		handler.newPost(TypeVehicle.Auto, 4, "aLocation", "aDescription", 
				"11111111", "availability", "", 100, 500, 
				"addressToDrop", "addressToPickUp", owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidPrice_itShouldThrowIllegalArgumentException() {		
		handler.newPost(TypeVehicle.Auto, 4, "aLocation", "aDescription", 
				"11111111", "availability", "photo", -23, 500, 
				"addressToDrop", "addressToPickUp", owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidAddressToDrop_itShouldThrowIllegalArgumentException() {		
		handler.newPost(TypeVehicle.Auto, 4, "aLocation", "aDescription", 
				"11111111", "availability", "photo", 100, 500, 
				"", "addressToPickUp", owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAPostIsCreatedWithInvalidAddressToPickUp_itShouldThrowIllegalArgumentException() {		
		handler.newPost(TypeVehicle.Auto, 4, "aLocation", "aDescription", 
				"11111111", "availability", "photo", 100, 500, 
				"addressToDrop", "", owner);
	}

	public void ifAnPostIsCreatedWithValidsArguments_theOwnerShouldBeANewPost() {		
		handler.newPost(TypeVehicle.Auto, 4, "aLocation", "aDescription", 
				"11111111", "availability", "photo", 100, 500, 
				"addressToDrop", "addressToPickUp", owner);		
		assertEquals(1, owner.posts.size());
	}
}
