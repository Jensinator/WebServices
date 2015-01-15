package de.hszg.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.hszg.client.Measurement;

@XmlRootElement(name = "measurementCollection")
public class MeasurementCollection {

	private List<Measurement> measurements;
	
	public MeasurementCollection(){
		
	}
	
	@XmlElement(name = "measurements")
	public List<Measurement> getMeasurements() {
			return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = Collections.synchronizedList( measurements );
	}
	
	public void addMeasurement(Measurement measurement){
		synchronized (measurements) {
			this.measurements.add(measurement);
		}
	}
	
}
