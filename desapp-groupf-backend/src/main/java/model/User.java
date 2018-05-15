package model;

import java.util.ArrayList;
import java.util.List;

public class User extends Entity{
	private static final long serialVersionUID = 5054708596541854955L;
	private String cuil;
	private String address;
	private String name;
	private String email;
	private String password;
	private List<Post> posts;
	private List<Score> score;
	private User_State state;
	
	public User(String cuil, String address, String name,String mail, String pass) {
		this.cuil = cuil;
		this.address = address;
		this.name = name;
		this.email=mail;
		this.password = pass;
		this.posts = new ArrayList<Post>();
		this.score = new ArrayList<Score>();
		this.state = User_State.REGULAR;
	}
	
	public User() {
		this.state = User_State.REGULAR;
		this.posts = new ArrayList<Post>();
		this.score = new ArrayList<Score>();
	}
	
	public double getScore() {
		if(score.isEmpty()) {
			return 0; 
		}	
		
		double sumScores = 0;
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
	
	public List<Score> getScores() {
		return this.score;
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

	public void addScore(Score score) {
		this.score.add(score);
	}

	public User_State getState() {
		return state;
	}
	
	public void setState(User_State newState) {
		this.state = newState;
	}

	public String getPassword() {
		return password;
	}
}
