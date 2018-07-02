package webservice;

import java.util.List;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import model.User;
import service.UserService;

@Path("/users")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class UserRest {

    public static final int NUMBER_OF_USERS = 10;


    private UserService userService;
    

   @GET
   @Path("/all")
   @Produces("application/json")
   public List<User> getAllUsers() {
       List<User> users = this.userService.retriveAll();
       System.out.println(users.size());
       return users;
   }
   
   @GET
   @Path("/checkUser/{email}")
   @Produces("application/json")
   public Boolean checkMail(@PathParam("email") String mail){
	   return this.userService.checkMail(mail);
   }
   
   @GET
   @Path("user/byEmail/{email}")
   @Produces("application/json")
   public User getByMail(@PathParam("email") String mail){
	   return this.userService.getByEmail(mail);
   }
   
   
   @POST
   @Path("/new")
   @Produces("application/json")
   public ResponseEntity<String> newUser(@RequestBody User user){
	   this.userService.save(user);
	   return new ResponseEntity<String>("Usuario creado",HttpStatus.CREATED);
   }
   
   @PUT
   @Path("/{id}/update")
   @Produces("application/json")
   public ResponseEntity<String> updateUser(@PathParam("id") Integer id,@RequestBody User user){
	   try {
		this.userService.update(user);
	} catch (Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
	   return new ResponseEntity<String>("Usuario actualizado",HttpStatus.OK);
   }
   
   @DELETE
   @Path("/delete/{id}")
   @Produces("application/json")
   public void deleteUser(@PathParam("id") Integer id){
	   User savedUser = this.userService.getById(id);
	   this.userService.delete(savedUser);
   }
   
   @GET
   @Path("/user/{id}")
   @Produces("application/json")
   public User getUser(@PathParam("id") final Integer id){
	   return this.userService.getById(id);
   }
   
   @GET
   @Path("/userScore/{id}")
   @Produces("application/json")
   public double getUserScore(@PathParam("id") final Integer id){
	   User user = this.userService.getById(id);
	   return user.scoreAvg();
   }

    public void setUserService(final UserService userServ) {
        userService= userServ;
    }
    
    
    @PUT
    @Path("/user/credits/add/{idUser}/{credits}")
    @Produces("application/json")
    public void addCredits(@PathParam("idUser") final Integer id,@PathParam("credits") final float credits) throws Exception{
    	this.userService.addCredits(id,credits);
    	
    }
    
    @PUT
    @Path("/user/credits/remove/{idUser}/{credits}")
    @Produces("application/json")
    public void removeCredits(@PathParam("idUser") final Integer id,@PathParam("credits") final float credits) throws Exception{
    	this.userService.removeCredits(id,credits);
    	
    }
    
}