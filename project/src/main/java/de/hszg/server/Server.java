package de.hszg.server;

import java.io.File;
import java.io.FileInputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import de.hszg.service.MeasurementReceiver;
import de.hszg.service.SensorCollection;

@Path("/server")
public class Server {

	static Logger log = Logger.getLogger( MeasurementReceiver.class.getName());
	
	@POST
	@Path("/sendCollection")
	@Consumes(MediaType.APPLICATION_XML)
	public Response sendCollection( SensorCollection sensorCollection,
			@HeaderParam("User") final String user, @HeaderParam("Key") final String key){
		
		if( user.equals("Nutte Nr.1 aus Datenbank") ){
			log.info("Die Nutte war schneller als Maik.");
			
			if( key.equals("0190666666") ){
				log.info("Level Up Sie ist eine Edelhure!");
			}
			
		}else{
			log.info("Maik hat die Nutte abgefangen!");
			return Response.status(401).entity( "Maik klatscht nicht nur mit den Händen!" ).build();
		}
		
		
		return Response.status(200).entity( "Everything was good" ).build();
	}
	
	@GET
	@Path("/getSex")
	@Produces(MediaType.APPLICATION_XML)
	public String sayHtmlHello() {
	
		log.info("This is our first log!");
		
	    return "<html> " + "<title>" + "Ronny" + "</title>"
	        + "<body><h1>" + "Ronny" + "</body></h1>" + "</html> ";
	}
	
}
