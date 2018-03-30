package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

	@Test
	public void ifAnUserHasNoQualifications_hisScoreShouldBeZero() {
		User anUser = Build.anUser().withScores(new ArrayList<Score>()).build();
		assertEquals(0, anUser.getScore(), 0);
	}
	
	@Test
	public void ifAnUserHasTwoQualificationsWithValues4And6_hisScoreShouldBe5() {
		List<Score> scores = new ArrayList<Score>();
		scores.add(Build.aScore().withValue(4).build());
		scores.add(Build.aScore().withValue(6).build());
		
		User anUser = Build.anUser().withScores(scores).build();
		assertEquals(5, anUser.getScore(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidCUIT_itShouldThrowIllegalArgumentException() {
		HandlerUser handler = new HandlerUser();
		handler.newUser("1000000000","Calle falsa 123", "Carlos","carlito@gmail.com");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidAddress_itShouldThrowIllegalArgumentException() {
		HandlerUser handler = new HandlerUser();
		handler.newUser("20390960299","", "Carlos","carlito@gmail.com");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidName_itShouldThrowIllegalArgumentException() {
		HandlerUser handler = new HandlerUser();
		handler.newUser("20390960299","Calle falsa 123", "Car","carlito@gmail.com");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidEmail_itShouldThrowIllegalArgumentException() {
		HandlerUser handler = new HandlerUser();
		handler.newUser("20390960299","Calle falsa 123", "Carlos","carlitogmail.com");
	}
	
	@Test
	public void ifAnUserIsCreatedWithValidsArguments_itShouldReturnANewUser() {
		HandlerUser handler = new HandlerUser();
		Assert.assertTrue(handler.newUser("20390960299","Calle falsa 123", "Carlos","carlito@gmail.com") != null);
	}
}
