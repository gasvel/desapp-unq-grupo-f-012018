package webservice;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import org.apache.log4j.Logger;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class AuthFilter implements ContainerRequestFilter {
	
	private GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
		    .setAudience(Collections.singletonList("663771392055-5epsk5veaf196v3qmnsnjuo9f1erndeb.apps.googleusercontent.com"))
		    .build();
	
	private List<String> whitelist = new ArrayList<String>();
	
	private final static Logger logger = Logger.getLogger("Filter");
	
	public AuthFilter(){
		super();
		whitelist.add("/posts/post");
		whitelist.add("/posts/all");
		whitelist.add("/checkUser");
	}
	

	public void filter(ContainerRequestContext req) throws IOException {
		
		Boolean inWhitelist = false;
		
		for(String str: whitelist){
			inWhitelist = inWhitelist || req.getUriInfo().getAbsolutePath().toString().contains(str);
		}
		
		if(!inWhitelist && req.getHeaders().containsKey("Authorization")){
				String token = (req.getHeaders().get("Authorization").get(0));
				try {
					GoogleIdToken idToken = this.verifier.verify(token);
					if(idToken == null){
						throw new IOException("Invalid token");
					}
					else{
						  Payload payload = idToken.getPayload();

						  // id del user
						  String userId = payload.getSubject();
						  System.out.println("User ID: " + userId);

						  // Informacion del perfil
						  String email = payload.getEmail();
						  String name = (String) payload.get("name");
						  String locale = (String) payload.get("locale");
						  String givenName = (String) payload.get("given_name");
						  
						  logger.info(givenName + " | " + name + " | " + email + " | " + locale);
					};
				} 
				catch (GeneralSecurityException e) {
					logger.error("Fallo en autenticacion", e);
				}

			}
		
		
	}

}
