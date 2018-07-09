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

import com.gaa.gaaservice.dto.Holiday;
import com.gaa.gaaservice.repository.HolidayRepository;

@Path("holiday")
public class HolidayResource {
HolidayRepository repo = new HolidayRepository();
	
	@GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Holiday> getHolidays() {	
		
        return repo.getHoliday();
    }
}