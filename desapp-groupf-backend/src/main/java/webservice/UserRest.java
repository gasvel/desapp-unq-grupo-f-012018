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

import model.Credential;
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
   
   @POST
   @Path("/new")
   @Produces("application/json")
   public void newUser(@RequestBody User user){
	   System.out.println(user.getCuil());
	   this.userService.save(user);
   }
   
   @PUT
   @Path("/{id}/update")
   @Produces("application/json")
   public void updateUser(@RequestParam("id") long id,@RequestBody User user){
	   this.userService.update(user);
   }
   
   @DELETE
   @Path("/delete/{id}")
   @Produces("application/json")
   public void deleteUser(@RequestParam("id") long id){
	   User savedUser = this.userService.getById(id);
	   this.userService.delete(savedUser);
   }
   
   @GET
   @Path("/user/{id}")
   @Produces("application/json")
   public User getUser(@RequestParam("id") long id){
	   return this.userService.getById(id);
   }

    public void setUserService(final UserService userServ) {
        userService= userServ;
    }
    
    @POST
    @Path("/login")
    @Produces("application/json")
	public User login(@RequestBody Credential cred) {
		User user = this.userService.getByEmail(cred.email);
		if(user == null) {
			throw new RuntimeException("No existe usuario con ese username");
		}
		if(user.getPassword() != cred.password) {
			throw new RuntimeException("Password incorrecto");
		}
		
		return user;
		
	}
    
}