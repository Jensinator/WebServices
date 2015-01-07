package de.hszg.client;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "measurement")
public class Measurement {
							
		private Timestamp timestamp;
		private long value;
		private int id;
		
		public Measurement(){}
		
		public Measurement(Timestamp timestamp, long value, int id){
			
			this.timestamp = timestamp;
			this.value = value;
			this.id = id;
		}
		
		@XmlJavaTypeAdapter( TimestampAdapter.class)
		@XmlElement(name = "timestamp")
		public Timestamp getTimestamp() {
			return timestamp;
		}
		
		public void setTimestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
		}
		
		@XmlElement(name = "value")
		public long getValue() {
			return value;
		}
		
		public void setValue(long value) {
			this.value = value;
		}
		
		@XmlElement(name = "id")
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		
}
