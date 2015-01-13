package de.hszg.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import de.hszg.client.Sensor;

@Path("/receiver")
public class MeasurementReceiver {

	static Logger log = Logger.getLogger( MeasurementReceiver.class.getName());
	
	@POST
	@Path("/sendValues")
	@Consumes(MediaType.APPLICATION_XML)
	public Response sendValues( Sensor sensor ){
		
		// do here the stuff we want to do with our service
		log.info("This is our first log!");
		
		return Response.status(200).entity( "Everything was good" ).build();
	}
	
	@GET
	@Path("/getHello")
	@Produces(MediaType.APPLICATION_XML)
	public String sayHtmlHello() {
		
		
		log.info("This is our first log!");
		
	    return "<html> " + "<title>" + "Hello" + "</title>"
	        + "<body><h1>" + "Hello" + "</body></h1>" + "</html> ";
	}
	
}
