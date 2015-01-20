package de.hszg.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import de.hszg.client.Measurement;

@Path("/receiver")
public class MeasurementReceiver {

	static Logger log = Logger.getLogger( MeasurementReceiver.class.getName());
	static Impl implementation = new Impl();
	
	
	public MeasurementReceiver() {
		// ctor
	}
	
	
	@POST
	@Path("/sendValues")
	@Consumes(MediaType.APPLICATION_XML)
	public Response sendValues( Measurement measurement, @HeaderParam("User") final String user,
			@HeaderParam("Key") final String key ) throws NoSuchAlgorithmException, KeyManagementException{
		
		// TODO: Remove user and key from param list
		
		// Add the measurement date into the cache
		SlutHoe data = new SlutHoe();
		data.setMeasurement(measurement);
		data.setUsername(user);
		data.setPassword(key);
		implementation.addMeasurement(data);
		
		// note: The service sends data to the server in time intervals.
		// 		 So this functionality is implemented in Impl!
		
		return Response.status(200).entity( "Everything was good" ).build();
	}
	
	@GET
	@Path("/getHello")
	@Produces(MediaType.APPLICATION_XML)
	public String sayHtmlHello( @HeaderParam("User") final String user,
			@HeaderParam("Key") final String key ) {
		
		log.info( "Header user : " +  user);
		log.info( "Header key  : " +  key );
		
		
		log.info("This is our first log!");
		
	    return "<html> " + "<title>" + "Hello" + "</title>"
	        + "<body><h1>" + "Hello" + "</body></h1>" + "</html> ";
	}
	
}
