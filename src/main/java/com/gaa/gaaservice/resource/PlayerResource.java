package com.gaa.gaaservice.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gaa.gaaservice.dto.Player;
import com.gaa.gaaservice.dto.Booking;
import com.gaa.gaaservice.dto.Fixture;
import com.gaa.gaaservice.repository.PlayerRepository;

@Path("player")
public class PlayerResource {
PlayerRepository repo = new PlayerRepository();
	
	@GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Player> getPlayers() {	
		
        return repo.getPlayer();
    }

	@POST
	@Path("fullname")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Player getPlayerByFullName(String json) {
		
		
		
        return repo.getPlayerByFullName(json);
    }
	@POST
	@Path("team")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Player> getPlayerByTeam(String json) {
		
		
		
        return repo.getPlayerByTeam(json);
    }
	@POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addPlayer(String player) {
		
			repo.addPlayer(player);
			return Response.ok(player).build();
    }
}
