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

import com.gaa.gaaservice.dto.Fixture;
import com.gaa.gaaservice.repository.FixtureRepository;


@Path("fixture")
public class FixtureResource {
	
	FixtureRepository repo = new FixtureRepository();
	
	@GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Fixture> getFixtures() {	
		
        return repo.getFictures();
    }

	
	@GET
	@Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Fixture getFixture(@PathParam("id") int id) {
		
		
		
        return repo.getFicture(id);
    }
	

	@POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addFixture(Fixture fixture) {
		// validate the fixture -> if wrong complain
		// check if exists in db -> complain
		// othervice save
		
		
			repo.addFicture(fixture);
			return Response.ok(fixture).build();
    }
}
