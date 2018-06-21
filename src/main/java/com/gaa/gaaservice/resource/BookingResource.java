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

import com.gaa.gaaservice.dto.Booking;
import com.gaa.gaaservice.repository.BookingRepository;

@Path("booking")
public class BookingResource {
BookingRepository repo = new BookingRepository();
	
	@GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Booking> getBookings() {	
		
        return repo.getBooking();
    }

	
	

	@POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addBooking(String booking) {
		
	
		
		
			repo.addBooking(booking);
			return Response.ok(booking).build();
    }
}
