package de.hszg.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

public class Generator {

	
	
	public static void main(String args[]) throws IOException, NoSuchAlgorithmException, KeyManagementException{
		
		String url = "https://127.0.0.1:8443/project/receiver/sendValues";
		
		ArrayList<Sensor> sensors = new ArrayList<Sensor>();
		
		for (int i = 0; i < 5; i++) {
			
			ArrayList<Measurement> measurements = new ArrayList<Measurement>();
			
			for (int j = 0; j < 100; j++) {
				
				Measurement measurement = new Measurement( new Timestamp(System.currentTimeMillis()), (long)Math.random(), j);
				measurements.add(measurement);
			}
			
			Sensor sensor = new Sensor(i , "Sensor" + i , i + 1000, "kg", measurements);
			sensors.add(sensor);
		}
		
		
		

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
		
		for (int i = 0; i < sensors.size(); i++) {
			
			//Client client = Client.create();
	        WebResource webResource = client.resource( url );
	        ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, sensors.get(i) );
	        
	        if( response.getStatus() != 200 ){
	        	throw new RuntimeException("Maik der Nuttenklatscher war am Werke: " + response.getStatus());
	        }
	        else{
	        	System.out.println("All right!!!!! :)");
	        }
		}
	}
}
