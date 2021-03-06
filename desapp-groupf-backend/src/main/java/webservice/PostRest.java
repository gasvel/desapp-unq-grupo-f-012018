package webservice;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import model.Post;
import model.TypeVehicle;
import model.User;
import service.PostService;
import service.UserService;

@Path("/posts")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class PostRest {
	
	public static final int NUMBER_OF_POSTS = 10;
	
	private PostService postService;
	private UserService userService;
	
	
	@GET
	@Path("/all")
    @Produces("application/json")
	public List<Post> getAllPosts() {
		List<Post> users = this.postService.retriveAll();
	    System.out.println(users.size());
	    return users;
	}
	
	@POST
	@Path("/{id}/new")
	@Produces("application/json")
	public ResponseEntity<String> newPost(@RequestBody Post post,@PathParam("id") final Integer id){
		post.setCreator(this.userService.getById(id));
		this.postService.save(post);
		return new ResponseEntity<String>("Post creado",HttpStatus.CREATED);
	}
	
	@PUT
	@Path("{id}/update")
	@Produces("application/json")
    public ResponseEntity<String> updatePost(@PathParam("id") Integer id,@RequestBody Post post){
		User creator = this.userService.getById(id);
		post.setCreator(creator);
		this.postService.update(post);
		return new ResponseEntity<String>("Post actualizado",HttpStatus.OK);
	}
	   
	@DELETE
	@Path("/delete/{id}")
	@Produces("application/json")
	public void deletePost(@PathParam("id") Integer id){
		Post postToDelete = this.postService.getById(id);
		this.postService.delete(postToDelete);
	}
	
	@GET
	@Path("/vehicle/types")
	@Produces("application/json")
	public TypeVehicle[] getVehicleTypes(){
		return TypeVehicle.values();
	}
	
	@GET
	@Path("/post/{id}")
	@Produces("application/json")
	public Post getPost(@PathParam("id") Integer id){
		return this.postService.getById(id);
	}

	public void setPostService(final PostService postSer) {
		postService = postSer;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	

}
