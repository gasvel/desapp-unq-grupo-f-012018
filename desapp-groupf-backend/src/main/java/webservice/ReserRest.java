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
import org.springframework.web.bind.annotation.RequestBody;

import model.ArgumentsValidator;
import model.Post;
import model.Reservation;
import model.User;
import service.PostService;
import service.ReserService;
import service.UserService;
import service.RentService;

@Path("/reservations")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class ReserRest {

	public static final int NUMBER_OF_RESER = 10;
	
	private ReserService reserService;
	private PostService postService;
	private UserService userService;
	private RentService rentService;
	
	
	@GET
	@Path("/all")
    @Produces("application/json")
	public List<Reservation> getAllReser() {
		List<Reservation> resers = this.reserService.retriveAll();
	    return resers;
	}
	
	@POST
	@Path("/new/{idPost}/{mailUser}")
	@Produces("application/json")
	public void newReser(@PathParam("idPost") final Integer idPost, @PathParam("mailUser") String emailUser,  @RequestBody Reservation reser){
		Post post = this.postService.getById(idPost);
		User user = this.userService.getByEmail(emailUser);
		reser.setClient(user);
		reser.generateTimeRent();
		reser.setPost(post);
		this.reserService.saveWithPost(reser, post);
	}
	
	@GET
	@Path("/from/{mailUser}")
	@Produces("application/json")
	public List<Reservation> getReservationsFromUser(@PathParam("mailUser") String emailUser){
		List<Reservation> allReser = this.reserService.getAllFromCreator(emailUser);
		return allReser;
	}
	
	@PUT
	@Path("/confirm")
	@Produces("application/json")
    public void confirmReservation(@RequestBody Reservation reser){
		Post post = reser.getPost();
		this.rentService.newRent(reser);
		post.removeReservation(reser);
		this.reserService.delete(reser);
		this.postService.update(post);
	}
	

	
	
	@DELETE
	@Path("/cancel/{id}")
	@Produces("application/json")
    public void cancelReservation(@PathParam("id") Integer id){
		Reservation reser = this.reserService.getById(id);
		Post post = reser.getPost();
		post.removeReservation(reser);
		this.reserService.delete(reser);
		this.postService.update(post);
	}
	
	@PUT
	@Path("/{id}/update")
	@Produces("application/json")
    public void updateReser(@PathParam("id") Integer id,@RequestBody Reservation reser, @RequestBody Post post){
		ArgumentsValidator.validateReserv(reser, post);
		this.reserService.update(reser);
	}
	   
	@DELETE
	@Path("/delete/{id}")
	@Produces("application/json")
	public void deleteReser(@PathParam("id") Integer id){
		Reservation reserToDelete = this.reserService.getById(id);
		this.reserService.delete(reserToDelete);
	}
	   
	@GET
	@Path("/reservation/{id}")
	@Produces("application/json")
	public Reservation getReser(@PathParam("id") Integer id){
		return this.reserService.getById(id);
	}

	public void setReserService(final ReserService reserServ) {
		reserService = reserServ;
	}

	public void setPostService(final PostService postServ){
		postService = postServ;
	}

	public void setUserService(final UserService userServ){
		userService = userServ;
	}
	
	public void setRentService(final RentService rentServ){
		rentService = rentServ;
	}
}
