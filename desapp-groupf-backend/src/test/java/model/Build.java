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
		
		public UserBuilder withCuil(String cuil) {
			anUser.setCuil(cuil);
			return this;
		}
		
		public UserBuilder withPost(Post post) {
			anUser.addPost(post);
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
	
	public static class RentBuilder{
		private Rent aRent = new Rent();
		
		public RentBuilder withUser(User user) {
			aRent.setClient(user);
			return this;
		}
		
		public Rent build() {
			
			return aRent;
		}
	}
	
	public static RentBuilder aRent() {
		return new RentBuilder();
	}
	
	public static class PostBuilder{
		private Post aPost = new Post();
		
		public Post build() {
			return aPost;
		}
	}
	
	public static PostBuilder aPost() {
		return new PostBuilder();
	}
	
}


