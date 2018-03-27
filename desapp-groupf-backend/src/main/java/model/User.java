package model;

import java.util.ArrayList;
import java.util.List;

public class User {
	String cuil;
	String address;
	String name;
	List<Post> posts;
	List<Score> score;
	
	public User(String cuil, String address, String name) {
		this.cuil = cuil;
		this.address = address;
		this.name = name;
		this.posts = new ArrayList<Post>();
		this.score = new ArrayList<Score>();
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
	
	public void createPost(TypeVehicle type, Integer capacity,
			String location,String description,String phoneNumber,
			String availability,String photo,Integer priceH,Integer priceD,String addressToDrop,String addressToPickUp) {
		
		Post newPost = new Post(type,capacity,location,description,phoneNumber,
				availability,photo,priceH,priceD,addressToDrop,addressToPickUp);
		
		this.posts.add(newPost);
	}
}
