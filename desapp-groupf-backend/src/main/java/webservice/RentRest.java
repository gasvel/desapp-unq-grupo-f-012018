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

import model.Rent;
import model.User;
import service.RentService;
import service.UserService;



@Path("/rents")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class RentRest {

	public static final int NUMBER_OF_RENTS = 10;
	
	private RentService rentService;
	private UserService userService;

	
	
	@GET
	@Path("/all")
    @Produces("application/json")
	public List<Rent> getAllRents() {
		List<Rent> rents = this.rentService.retriveAll();
	    System.out.println(rents.size());
	    return rents;
	}
	
	@POST
	@Path("/new")
	@Produces("application/json")
	public void newRent(@RequestBody Rent rent){
		this.rentService.save(rent);
	}
	
	@PUT
	@Path("/{id}/update")
	@Produces("application/json")
    public void updateRent(@RequestParam("id") long id,@RequestBody Rent rent){
		this.rentService.update(rent);
	}
	   
	@DELETE
	@Path("/delete/{id}")
	@Produces("application/json")
	public void deleteRent(@PathParam("id") Integer id){
		Rent rentToDelete = this.rentService.getById(id);
		this.rentService.delete(rentToDelete);
	}
	   
	@GET
	@Path("/rent/{id}")
	@Produces("application/json")
	public Rent getRent(@PathParam("id") Integer id){
		return this.rentService.getById(id);
	}
	
	@PUT
	@Path("/confirmPickUp/{mail}")
	@Produces("application/json")
	public void confirmRent(@PathParam("mail") String mail,@RequestBody Rent rent){
		User user = this.userService.getByEmail(mail);
		this.rentService.confirmPickUp(rent,user);
	}
	
	@PUT
	@Path("/confirmVehicleReturn/{mail}/{score}")
	@Produces("application/json")
	public void confirmVehicleReturns(
			@PathParam("mail") String mail,
			@PathParam("score") Integer score,
			@RequestBody Rent rent){
		User user = this.userService.getByEmail(mail);
		user = this.rentService.confirmVehicleReturns(rent,user,score);
		this.userService.update(user);
	}
	
	@GET
	@Path("/toConfirm/owner/{mail}")
	@Produces("application/json")
	public List<Rent> rentsToConfirmOwner(@PathParam("mail") String mail){
		return this.rentService.allToConfirmOwner(mail);
	}
	
	@GET
	@Path("/toConfirm/client/{mail}")
	@Produces("application/json")
	public List<Rent> rentsToConfirmClient(@PathParam("mail") String mail){
		return this.rentService.allToConfirmClient(mail);
	}
	
	@GET
	@Path("/rentsDone/owner/{mail}")
	@Produces("application/json")
	public List<Rent> rentsDoneOwner(@PathParam("mail") String mail){
		return this.rentService.allRentsDoneOwner(mail);
	}
	
	@GET
	@Path("/rentsDone/client/{mail}")
	@Produces("application/json")
	public List<Rent> rentsDoneClient(@PathParam("mail") String mail){
		return this.rentService.allRentsDoneClient(mail);
	}

	public void setRentService(final RentService rentSer) {
		rentService = rentSer;
	}
	
	public void setUserService(final UserService userServ){
		userService = userServ;
	}

}
