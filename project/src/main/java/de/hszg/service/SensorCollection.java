package de.hszg.service;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.hszg.client.Sensor;

@XmlRootElement(name = "sensorCollection")
public class SensorCollection {

	private ArrayList<Sensor> sensors;
	
	public SensorCollection(){
		
	}
	
	@XmlElement(name = "sensors")
	public ArrayList<Sensor> getSensors() {
		return sensors;
	}
	
	
}
