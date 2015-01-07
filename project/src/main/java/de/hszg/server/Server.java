package de.hszg.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import de.hszg.service.SensorCollection;

@Path("/server")
public class Server {

	@POST
	@Path("/sendCollection")
	@Consumes(MediaType.APPLICATION_XML)
	public Response sendCollection( SensorCollection sensorCollection){
		
		// do here the stuff we want to do with our service
		
		
		return Response.status(200).entity( "Everything was good" ).build();
	}
}
