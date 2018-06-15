package com.gaa.gaaservice.security;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import com.gaa.gaaservice.dto.User;

@Provider
public class SecurityFilter implements ContainerRequestFilter{
	
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	
	private UserRepository userRepository = new UserRepository();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		// case of specific path needs auth use this:
		//boolean isUserPath = requestContext.getUriInfo().getPath().contains("user/");
		
		/*if(isUserPath && requestContext.getMethod().equals("POST"))
		{
			return;
		}*/
		
		
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		
		if( authHeader != null && authHeader.size() > 0) {
			String authToken = authHeader
					.get(0)
					.replaceAll(AUTHORIZATION_HEADER_PREFIX, "");
			
			String decodedString = Base64.decodeAsString(authToken);
			StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
			
			String username = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			
			User user = userRepository.getUserByEmail(username);
			
			if(user != null && user.getPassword().equals(password)) {
				return;
			}
			else {

				Response unauthorizedStatus = Response
						.status(Response.Status.UNAUTHORIZED)
						.entity("Username or password in incorect")
						.build();

				requestContext.abortWith(unauthorizedStatus);			
			}
			
		}
		else {
			Response unauthorizedStatus = Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("Invalid token")
					.build();

			requestContext.abortWith(unauthorizedStatus);
		}
		
		
		
	}

}
