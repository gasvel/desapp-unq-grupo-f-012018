package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
}
