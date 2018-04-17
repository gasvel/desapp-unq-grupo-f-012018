package webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.User;
import service.UserService;

@Path("/posts")
public class UserRest {

    public static final int NUMBER_OF_POST = 10;

    /**
     * IMPORTANTE!! - Acá deben inyectar el servicio de la aplicación y este
     * servicio REST solo debe hacer un delegate simple.
     */
    private UserService userService;

   @GET
   @Path("/users")
   @Produces("application/json")
   public List<User> getAllUsers() {
       List<User> users = userService.retriveAll();
       return users;
   }

    public void setUserService(final UserService userServ) {
        userService= userServ;
    }
}