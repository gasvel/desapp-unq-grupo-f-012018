package webservice;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import model.Rent;
import service.RentService;



@Path("/rents")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class RentRest {

	public static final int NUMBER_OF_RENTS = 10;
	
	private RentService rentService;
	
	
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
	public void deleteRent(@RequestParam("id") long id){
		Rent rentToDelete = this.rentService.getById(id);
		this.rentService.delete(rentToDelete);
	}
	   
	@GET
	@Path("/rent/{id}")
	@Produces("application/json")
	public Rent getRent(@RequestParam("id") long id){
		return this.rentService.getById(id);
	}

	public void setRentService(final RentService rentSer) {
		rentService = rentSer;
	}

}
