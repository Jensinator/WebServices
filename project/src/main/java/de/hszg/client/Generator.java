package de.hszg.client;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Generator {

	
	
	public static void main(String args[]){
		
		String url = "http://127.0.0.1:8080/project/receiver/sendValues";
		
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
		
		for (int i = 0; i < sensors.size(); i++) {
			
			Client client = Client.create();
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
