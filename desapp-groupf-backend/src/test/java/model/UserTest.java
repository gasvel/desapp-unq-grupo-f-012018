package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	public HandlerUser handler; 
	
	@Before
	public void setUp() throws Exception {
		handler = new HandlerUser();
	}

	@Test
	public void ifAnUserHasNoQualificationsHisScoreShouldBeZero() {
		User anUser = Build.anUser().withScores(new ArrayList<Score>()).build();
		assertEquals(0, anUser.scoreAvg(), 0);
	}
	
	@Test
	public void ifAnUserHasTwoQualificationsWithValues4And6hisScoreShouldBe5() {
		List<Score> scores = new ArrayList<Score>();
		scores.add(Build.aScore().withValue(4).build());
		scores.add(Build.aScore().withValue(6).build());
		
		User anUser = Build.anUser().withScores(scores).build();
		assertEquals(5, anUser.scoreAvg(), 0);
	}
	
	@Test
	public void isShoudBePossibleRateAnUser() {
		List<Score> scores = new ArrayList<Score>();
		User anUser = Build.anUser().withScores(scores).build();
		handler.rateUser(anUser, 3, "");
		assertEquals(1, anUser.getScore().size());
	}
	
	@Test
	public void ifAnUserHasAnAverageScoreOf3AndLessOf10ScoresheShouldntBeBanned() {
		List<Score> scores = new ArrayList<Score>();
		User anUser = Build.anUser().withScores(scores).build();
		handler.rateUser(anUser, 3, "");
		handler.rateUser(anUser, 3, "");
		handler.rateUser(anUser, 3, "");
		
		assertEquals(3, anUser.getScore().size());
		assertEquals(3, anUser.scoreAvg(), 0);
		assertEquals(User_State.REGULAR, anUser.getState());
	}
	
	@Test
	public void ifAnUserHasAnAverageScoreGreaterThan4AndMoreOf10ScoresheShouldntBeBanned() {
		List<Score> scores = new ArrayList<Score>();
		User anUser = Build.anUser().withScores(scores).build();
		handler.rateUser(anUser, 1, "");
		handler.rateUser(anUser, 5, "");
		handler.rateUser(anUser, 5, "");
		handler.rateUser(anUser, 5, "");
		handler.rateUser(anUser, 5, "");
		handler.rateUser(anUser, 4, "");
		handler.rateUser(anUser, 4, "");
		handler.rateUser(anUser, 4, "");
		handler.rateUser(anUser, 4, "");
		handler.rateUser(anUser, 4, "");
		
		assertEquals(10, anUser.getScore().size());
		assertEquals(4.1, anUser.scoreAvg(), 0);
		assertEquals(User_State.REGULAR, anUser.getState());
	}
	
	@Test
	public void ifAnUserHasAnAverageScoreLessThan4AndMoreOf10ScoresheShouldBeBanned() {
		List<Score> scores = new ArrayList<Score>();
		User anUser = Build.anUser().withScores(scores).build();
		handler.rateUser(anUser, 1, "");
		handler.rateUser(anUser, 1, "");
		handler.rateUser(anUser, 5, "");
		handler.rateUser(anUser, 5, "");
		handler.rateUser(anUser, 5, "");
		handler.rateUser(anUser, 4, "");
		handler.rateUser(anUser, 4, "");
		handler.rateUser(anUser, 4, "");
		handler.rateUser(anUser, 4, "");
		handler.rateUser(anUser, 4, "");
		
		assertEquals(10, anUser.getScore().size());
		assertEquals(3.7, anUser.scoreAvg(), 0);
		assertEquals(User_State.BANNED, anUser.getState());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidCUITitShouldThrowIllegalArgumentException() {
		User anUser = Build.anUser().valid().build();
		anUser.setCuil("1000000000");
		handler.newUser(anUser);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidAddressitShouldThrowIllegalArgumentException() {
		User anUser = Build.anUser().valid().build();
		anUser.setAddress("");
		handler.newUser(anUser);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidNameitShouldThrowIllegalArgumentException() {
		User anUser = Build.anUser().valid().build();
		anUser.setName("Car");
		handler.newUser(anUser);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidEmailitShouldThrowIllegalArgumentException() {
		User anUser = Build.anUser().valid().build();
		anUser.setEmail("carlitogmail.com");
		handler.newUser(anUser);
	}
	
	@Test
	public void ifAnUserIsCreatedWithValidsArgumentsitShouldReturnANewUser() {
		User anUser = Build.anUser().valid().build();
		assertTrue(handler.newUser(anUser) != null);
	}
}
