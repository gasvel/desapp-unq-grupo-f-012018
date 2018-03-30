package model;

import java.util.List;

public class Build {
	//User builder
	public static class UserBuilder {
		private User anUser = new User();		
		
		public UserBuilder withScores(List<Score> scores) {
			anUser.setScores(scores);
			return this;
		}
		
		public User build() {
			return anUser;
		}
	}
	
	public static UserBuilder anUser() {
		return new UserBuilder();
	}
	
	//Score builder
	public static class ScoreBuilder {
		 private Score aScore = new Score("Description test");
		
		public ScoreBuilder withValue(Integer value) {
			aScore.setValue(value);
			return this;
		}
		
		public Score build() {
			return aScore;
		}
	}
	
	public static ScoreBuilder aScore() {
		return new ScoreBuilder();
	}
	
}


