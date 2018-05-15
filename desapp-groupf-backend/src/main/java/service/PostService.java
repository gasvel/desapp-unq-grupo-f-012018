package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import model.ArgumentsValidator;
import model.Post;

public class PostService extends GenericService<Post>{

	private static final long serialVersionUID = -5933797928208038356L;

	@Override
	@Transactional
	public void save(Post post){
		ArgumentsValidator.validatePost(post);
		super.save(post);
	}
	
	@Override
	@Transactional
	public void update(Post post){
		ArgumentsValidator.validatePost(post);
		super.update(post);
	}
	
	@Override
	@Transactional
	public void delete(Post post){
		super.delete(post);
	}
	
	@Override
	@Transactional
	public Post getById(Integer id){
		return super.getById(id);
	}
	
	@Override
	@Transactional
	public List<Post> retriveAll(){
		return super.retriveAll();
	}
}
