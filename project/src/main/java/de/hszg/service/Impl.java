package de.hszg.service;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import de.hszg.client.Measurement;


class Impl{
	
	static Logger log = Logger.getLogger( MeasurementReceiver.class.getName());
	Timer timer;
	MeasurementCollection measurements;

	/** This class is used to send data in time intervalls
	 *  in a parallel way.
	 * */
	class Transmitter extends TimerTask {
		
		Impl parent;

		public Transmitter( Impl parent ){
			log.info("Transmitter is called");
			this.parent = parent;
		}

		public void run() {
			log.info("run is called");
			try {
				parent.sendToServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
	
	/** Constructor
	 * 	Creates and start the timer in parallel.
	 * */
	public Impl(){
		log.info("Pisse aus meinem Arsch!");
		timer = new Timer();
		timer.schedule( new Transmitter(this), 5000, 5000 );
		measurements = new MeasurementCollection();
		measurements.setMeasurements( new ArrayList<SlutHoe>() );
	}

	public synchronized void sendToServer( ) throws Exception {

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
		
	    WebResource webResource = client.resource( url );
	    // Achtung! Diese Nummer kann sehr teuer werden.
	    ClientResponse response = webResource.header("User", "Maik").header("Key","0190666666").accept(MediaType.APPLICATION_XML).post(ClientResponse.class, measurements );
	        
	    if( response.getStatus() != 200 ){
	        throw new RuntimeException("Maik der Nuttenklatscher war am Werke: " + response.getStatus());
	    }
	    else{
	    	measurements.clear();
	    	System.out.println("All right!!!!! :)");
	    }
	    
	}
	
	public synchronized void addMeasurement( SlutHoe measurement ){
		this.measurements.addMeasurement(measurement);
	}
	
}