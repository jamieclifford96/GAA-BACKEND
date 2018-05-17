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

import com.gaa.gaaservice.dto.Lotto;
import com.gaa.gaaservice.dto.Result;
import com.gaa.gaaservice.repository.LottoRepository;
import com.gaa.gaaservice.repository.ResultsRepository;


@Path("lotto")
public class LottoResource {
	
	LottoRepository repo = new LottoRepository();
	
	@GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Lotto> getLottos() {	
		
        return repo.getLottos();
    }

	/*
	@GET
	@Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Fixture getFixture(@PathParam("id") int id) {
		
		
		
        return repo.getFicture(id);
    }
	

	@POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Fixture addFixture(Fixture fixture) {
		
		
		
        return fixture;
    }
    */
}
