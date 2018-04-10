package model;

public class HandlerPost {
	
	public void newPost(Post post,User owner) {
		
		ArgumentsValidator.validatePost(post);
		
		owner.addPost(post);
	}
	

}
