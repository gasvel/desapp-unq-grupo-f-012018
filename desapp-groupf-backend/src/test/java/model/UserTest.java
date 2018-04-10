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
	
	@Test
	public void isShoudBePossibleRateAnUser() {
		List<Score> scores = new ArrayList<Score>();
		User anUser = Build.anUser().withScores(scores).build();
		handler.rateUser(anUser, 3, "");
		assertEquals(1, anUser.getScores().size());
	}
	
	@Test
	public void ifAnUserHasAnAverageScoreOf3AndLessOf10Scores_heShouldntBeBanned() {
		List<Score> scores = new ArrayList<Score>();
		User anUser = Build.anUser().withScores(scores).build();
		handler.rateUser(anUser, 3, "");
		handler.rateUser(anUser, 3, "");
		handler.rateUser(anUser, 3, "");
		
		assertEquals(3, anUser.getScores().size());
		assertEquals(3, anUser.getScore(), 0);
		assertEquals(User_State.REGULAR, anUser.getState());
	}
	
	@Test
	public void ifAnUserHasAnAverageScoreGreaterThan4AndMoreOf10Scores_heShouldntBeBanned() {
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
		
		assertEquals(10, anUser.getScores().size());
		assertEquals(4.1, anUser.getScore(), 0);
		assertEquals(User_State.REGULAR, anUser.getState());
	}
	
	@Test
	public void ifAnUserHasAnAverageScoreLessThan4AndMoreOf10Scores_heShouldBeBanned() {
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
		
		assertEquals(10, anUser.getScores().size());
		assertEquals(3.7, anUser.getScore(), 0);
		assertEquals(User_State.BANNED, anUser.getState());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidCUIT_itShouldThrowIllegalArgumentException() {
		handler.newUser("1000000000","Calle falsa 123", "Carlos","carlito@gmail.com");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidAddress_itShouldThrowIllegalArgumentException() {
		handler.newUser("20390960299","", "Carlos","carlito@gmail.com");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidName_itShouldThrowIllegalArgumentException() {
		handler.newUser("20390960299","Calle falsa 123", "Car","carlito@gmail.com");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ifAnUserIsCreatedWithInvalidEmail_itShouldThrowIllegalArgumentException() {
		handler.newUser("20390960299","Calle falsa 123", "Carlos","carlitogmail.com");
	}
	
	@Test
	public void ifAnUserIsCreatedWithValidsArguments_itShouldReturnANewUser() {
		assertTrue(handler.newUser("20390960299","Calle falsa 123", "Carlos","carlito@gmail.com") != null);
	}
}
