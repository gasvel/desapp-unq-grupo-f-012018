package model;

import exception.InvalidModelException;

public class HandlerPost {
	
	public void newPost(Post post,User owner) throws InvalidModelException {
		
		ArgumentsValidator.validatePost(post);
		
		post.setCreator(owner);
	}
	

}
