package webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.RequestBody;

import model.User;
import persistence.UserRepository;
import service.UserService;

@Path("/users")
public class UserRest {

    public static final int NUMBER_OF_POST = 10;

    /**
     * IMPORTANTE!! - Acá deben inyectar el servicio de la aplicación y este
     * servicio REST solo debe hacer un delegate simple.
     */
    private UserService userService;
    

   @GET
   @Path("/all")
   @Produces("application/json")
   public List<User> getAllUsers() {
       List<User> users = this.userService.retriveAll();
       return users;
   }
   
   @POST
   @Path("/new")
   @Produces("application/json")
   public void newUser(@RequestBody User user){
	   this.userService.save(user);
   }

    public void setUserService(final UserService userServ) {
        userService= userServ;
    }
    

    
    
}