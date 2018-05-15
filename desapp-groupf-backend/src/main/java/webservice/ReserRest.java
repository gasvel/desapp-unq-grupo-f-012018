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
import org.springframework.web.bind.annotation.RequestParam;

import model.ArgumentsValidator;
import model.Post;
import model.Reservation;
import service.ReserService;

@Path("/reservations")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class ReserRest {

	public static final int NUMBER_OF_RESER = 10;
	
	private ReserService reserService;
	
	
	@GET
	@Path("/all")
    @Produces("application/json")
	public List<Reservation> getAllReser() {
		List<Reservation> resers = this.reserService.retriveAll();
	    System.out.println(resers.size());
	    return resers;
	}
	
	@POST
	@Path("/new")
	@Produces("application/json")
	public void newReser(@RequestBody Reservation reser, @RequestBody Post post){
		ArgumentsValidator.validateReserv(reser, post);
		this.reserService.save(reser);
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


}
