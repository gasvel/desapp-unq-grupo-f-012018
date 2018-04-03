package model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String cuil;
	private String address;
	private String name;
	private String email;
	private List<Post> posts;
	private List<Score> score;
	
	public User(String cuil, String address, String name,String mail) {
		this.cuil = cuil;
		this.address = address;
		this.name = name;
		this.email=mail;
		this.posts = new ArrayList<Post>();
		this.score = new ArrayList<Score>();
	}
	
	public User() {
		this.posts = new ArrayList<Post>();
		this.score = new ArrayList<Score>();
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
	

	public void addPost(Post newPost) {
		this.posts.add(newPost);		
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void setScore(List<Score> score) {
		this.score = score;
	}
	
	
}
