package de.hszg.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.hszg.client.Measurement;

@XmlRootElement(name = "measurementCollection")
public class MeasurementCollection {

	private List<SlutHoe> measurements;
	
	public MeasurementCollection(){
		measurements = new ArrayList<SlutHoe>();
	}
	
	@XmlElement(name = "measurements")
	public List<SlutHoe> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<SlutHoe> measurements) {
		this.measurements =  measurements ;
	}
	
	public void addMeasurement(SlutHoe measurement){
		this.measurements.add(measurement);
	}
	
	public void clear(){
		this.measurements.clear();
	}
}
