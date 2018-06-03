package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

public class User extends Entity{
	private static final long serialVersionUID = 5054708596541854955L;
	private Integer id;
	private String cuil;
	private String address;
	private String name;
	private String email;
	private float credits;
	@JsonIgnore
	private Set<Post> posts;
	private List<Score> score;
	private User_State state;
	
	public User(String cuil, String address, String name,String mail) {
		this.cuil = cuil;
		this.address = address;
		this.name = name;
		this.email=mail;
		this.posts = new HashSet<Post>();
		this.score = new ArrayList<Score>();
		this.state = User_State.REGULAR;
		this.credits = 0;
	}
	
	public User() {
		this.state = User_State.REGULAR;
		this.posts = new HashSet<Post>();
		this.score = new ArrayList<Score>();
		this.credits = 0;
	}
	
	public double scoreAvg() {
		if(score.isEmpty()) {
			return 0; 
		}	
		
		double sumScores = 0;
		for (Score aScore : score) {
			sumScores += aScore.getValue();
		}
		return sumScores / score.size();
	}

	public void setScore(List<Score> score) {
		this.score = score;		
	}
	
	
	

	public List<Score> getScore() {
		return score;
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

	@JsonIgnore
	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
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



	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getCredits() {
		return credits;
	}

	public void setCredits(float credits) {
		this.credits = credits;
	}

	public void addCredits(float credits2) {
		this.credits += credits2;
		
	}
	
	public void removeCredits(float credits2){
		this.credits -= credits2;
	}
	
	
	
	
}
