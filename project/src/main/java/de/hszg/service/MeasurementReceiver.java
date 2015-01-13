package de.hszg.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;

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

import de.hszg.client.Sensor;

@Path("/receiver")
public class MeasurementReceiver {

	static Logger log = Logger.getLogger( MeasurementReceiver.class.getName());
	
	@POST
	@Path("/sendValues")
	@Consumes(MediaType.APPLICATION_XML)
	public Response sendValues( Sensor sensor, @HeaderParam("User") final String user,
			@HeaderParam("Key") final String key ) throws NoSuchAlgorithmException, KeyManagementException{
		
		// do here the stuff we want to do with our service
		log.info("This is our first log!");
		
		log.info( "Header user : " +  user);
		log.info( "Header key  : " +  key );
		
		String url = "https://127.0.0.1:8443/project/server/sendCollection";
		
		TrustManager[] certs = new TrustManager[]
		        {
		            new X509TrustManager()
		            {
						@Override
						public void checkClientTrusted(
								java.security.cert.X509Certificate[] arg0,
								String arg1) throws CertificateException {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void checkServerTrusted(
								java.security.cert.X509Certificate[] arg0,
								String arg1) throws CertificateException {
							// TODO Auto-generated method stub
							
						}

						@Override
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							// TODO Auto-generated method stub
							return null;
						}
		            }
		        };
		
		SSLContext ctx = SSLContext.getInstance("SSL");
		ctx.init(null, certs, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
		ClientConfig config = new DefaultClientConfig();

		
		ctx.init(null, certs, null);
		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties( new HostnameVerifier() {
			
			@Override
			public boolean verify(String hostname, SSLSession session) {
				// TODO Auto-generated method stub
				return true;
			}
		} , ctx));
		Client client = Client.create(config);
		
		
		SensorCollection sensorSet = new SensorCollection();
		ArrayList<Sensor> sensors = new ArrayList<Sensor>();
		sensors.add(sensor);
		sensorSet.setSensors(sensors);
		
			//Client client = Client.create();
	        WebResource webResource = client.resource( url );
	        ClientResponse response = webResource.header("User", user).header("Key", key).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, sensorSet );
	        
	        if( response.getStatus() != 200 ){
	        	throw new RuntimeException("Maik der Nuttenklatscher war am Werke: " + response.getStatus());
	        }
	        else{
	        	System.out.println("All right!!!!! :)");
	        }
		
		
		
		
		
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
