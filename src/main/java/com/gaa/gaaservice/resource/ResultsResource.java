package com.gaa.gaaservice.resource;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.gaa.gaaservice.dto.Result;
import com.gaa.gaaservice.repository.ResultsRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


@Path("results")
public class ResultsResource {
	
	ResultsRepository repo = new ResultsRepository();
	
	@GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Result> getResults() {	
		
        return repo.getResults();
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

	@POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addResult(String stringJson) {
              JsonDeserializer<Result> deserializer = new JsonDeserializer<Result>() {

				@Override
				public Result deserialize(JsonElement json, Type arg1,
						JsonDeserializationContext arg2) throws JsonParseException {
					JsonObject jsonObject = json.getAsJsonObject();
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
                    //DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
                    LocalDateTime dateTime = LocalDateTime.parse(jsonObject.get("dateTime").getAsString(), formatter);

               return new Result(
                           jsonObject.get("division").getAsString(),
                           jsonObject.get("home").getAsString(),
                           jsonObject.get("away").getAsString(),
                           jsonObject.get("homeScore").getAsString(),
                           jsonObject.get("awayScore").getAsString(),
                           dateTime
                           );
				}
                                    
              };
              
              GsonBuilder gsonBuilder = new GsonBuilder();
              gsonBuilder.registerTypeAdapter(Result.class, deserializer);

              Gson customGson = gsonBuilder.create();  
              Result result = customGson.fromJson(stringJson, Result.class); 
              
              // so some struff with result
              repo.addResult(result);
      		
      		
      		return Response.ok(result).build();

    }

}
