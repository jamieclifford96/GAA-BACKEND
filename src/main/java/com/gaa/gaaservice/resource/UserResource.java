package com.gaa.gaaservice.resource;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.gaa.gaaservice.dto.Fixture;
import com.gaa.gaaservice.dto.User;
import com.gaa.gaaservice.repository.UserRepository;


@Path("user")
public class UserResource {
	
	UserRepository repo = new UserRepository();
	
	@GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getUsers() {	
		
        return repo.getUsers();
    }
	@POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response  addUser(User user) {	
		
		repo.addUser(user);
		
		//in case user exists Response.status(Status.CONFLICT).build();
		
		return Response.ok(user).build();
		
       
    }
}
