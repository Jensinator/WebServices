package de.hszg.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hszg.client.Sensor;

@Path("/receiver")
public class MeasurementReceiver {

	
	@POST
	@Path("/sendValues")
	@Consumes(MediaType.APPLICATION_XML)
	public Response sendViews( Sensor sensor ){
		
		// do here the stuff we want to do with our service
		
		
		return Response.status(200).entity( "Everything was good" ).build();
	}
	
	@GET
	@Path("/getHello")
	@Produces(MediaType.APPLICATION_XML)
	public String sayHtmlHello() {
		
	    return "<html> " + "<title>" + "Hello" + "</title>"
	        + "<body><h1>" + "Hello" + "</body></h1>" + "</html> ";
	}
	
	
}
