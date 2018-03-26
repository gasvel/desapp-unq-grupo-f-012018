package model;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class User {
	String cuil;
	String address;
	String name;
	List<Post> posts;
	List<Score> score;
	
	public User(String cuil, String address, String name, List<Score> score, List<Post> posts) {
		this.cuil = cuil;
		this.address = address;
		this.name = name;
		this.posts = posts;
		this.score = score;
	}
	
	public User() {
	}
	
	public double getScore() {
		if(score.isEmpty()) {
			return 0; 
		}	
		
		Integer sumScores = 0;
		for (Score aScore : score) {
			sumScores += aScore.getValue();
		}
		return sumScores / score.size();
	}

	public void setScores(List<Score> score) {
		this.score = score;		
	}	
}
