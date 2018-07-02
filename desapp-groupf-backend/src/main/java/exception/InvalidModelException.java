package exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;


public class InvalidModelException extends ClientErrorException {

	private static final long serialVersionUID = 1L;

	public InvalidModelException(String message){
		super(Response.status(Response.Status.NOT_ACCEPTABLE).entity(message).build(),new Throwable(message));
	}
}
