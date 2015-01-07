package de.hszg.client;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sensor")
public class Sensor {
	private int ID;
	private String name;
	private int buildingID;
	private String unit;
	private ArrayList<Measurement> measurements;

	public Sensor(){}
	
	public Sensor(int ID, String name, int buildingID, String unit, ArrayList<Measurement> measurements) {
		this.ID = ID;
		this.name = name;
		this.buildingID = buildingID;
		this.measurements = measurements;
	}

	@XmlElement(name = "ID")
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "buildingID")
	public int getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(int buildingID) {
		this.buildingID = buildingID;
	}

	@XmlElement(name = "unit")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@XmlElement(name = "measurements")
	public ArrayList<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(ArrayList<Measurement> measurements) {
		this.measurements = measurements;
	}
}
