package de.hszg.server;

import java.io.File;
import java.io.FileInputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	public Response sendCollection( SensorCollection sensorCollection){
		
		// do here the stuff we want to do with our service
		File filePriK = new File("/privateKey.key");
		
		try{
			// Private Key lesen
			FileInputStream fis = new FileInputStream(filePriK);
			byte[] encodedPrivateKey = new byte[(int) filePriK.length()];
			fis.read(encodedPrivateKey);
			fis.close();
		}catch( Exception unexpected ){
			log.error("Pisse aus meinem Arsch : " + unexpected.getStackTrace());
		}
		
		return Response.status(200).entity( "Everything was good" ).build();
	}
	
	@GET
	@Path("/getSex")
	@Produces(MediaType.APPLICATION_XML)
	public String sayHtmlHello() {
		
		
		log.info("This is our first log!");
		
		
		// do here the stuff we want to do with our service
				File filePriK = new File("/privateKey.key");
				
				try{
					// Private Key lesen
					FileInputStream fis = new FileInputStream(filePriK);
					byte[] encodedPrivateKey = new byte[(int) filePriK.length()];
					fis.read(encodedPrivateKey);
					fis.close();
				}catch( Exception unexpected ){
					log.error("Pisse aus meinem Arsch : " + unexpected.getStackTrace());
				}
	    return "<html> " + "<title>" + "Ronny" + "</title>"
	        + "<body><h1>" + "Ronny" + "</body></h1>" + "</html> ";
	}
	
}
