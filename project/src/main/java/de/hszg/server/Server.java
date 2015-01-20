package de.hszg.server;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import de.hszg.service.MeasurementCollection;
import de.hszg.service.MeasurementReceiver;

@Path("/server")
public class Server {

	static Logger log = Logger.getLogger(MeasurementReceiver.class.getName());

	@POST
	@Path("/sendCollection")
	@Consumes(MediaType.APPLICATION_XML)
	public Response sendCollection(MeasurementCollection measurementCollection,
			@HeaderParam("User") final String user,
			@HeaderParam("Key") final String key) throws Exception {

		// Register JDBC driver
		Class.forName("org.postgresql.Driver");

		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/EADDB", "postgres", "123456");


		boolean denied = false;
			for (int i = 0; i < measurementCollection.getMeasurements().size(); i++) {

				PreparedStatement userStatement = connection.prepareStatement("SELECT \"Name\", \"Password\" FROM \"Accounts\" WHERE \"Name\" = ?");
				userStatement.setString(1, user);

				ResultSet userResults = userStatement.executeQuery();

				userResults.next();
				String userToCompare = userResults.getString(1);
				String keyToCompare = userResults.getString(2);

				if (user.equals(userToCompare)) {
					log.info("Die Nutte war schneller als Maik.");

					if (key.equals(keyToCompare)) {
							log.info("Level Up Sie ist eine Edelhure!");
					
					String sqlInsert = "INSERT INTO \"Measurements\"(\"Timestamp\",\"Value\", \"SensorID\") VALUES( ?, ?, ? )";
					PreparedStatement insertStatement = connection
							.prepareStatement(sqlInsert);
					insertStatement.setTimestamp(1, measurementCollection
							.getMeasurements().get(i).getMeasurement().getTimestamp());
					insertStatement.setInt(2, (int) measurementCollection
							.getMeasurements().get(i).getMeasurement().getValue());
					insertStatement.setInt(3, measurementCollection
							.getMeasurements().get(i).getMeasurement().getSensorID());
					insertStatement.execute();
					}
				
			}else {
				log.info("Maik hat die Nutte abgefangen!" + userToCompare + "   " + keyToCompare );
				return Response.status(401).entity("Maik klatscht nicht nur mit den Händen!").build();
			} 
		}
			log.info("Maik sended seine Schergen aus die eine Nutte zu finden um sie ewig zu binden!");
			if( !denied)
				return Response.status(200).entity("Everything was good").build();
			else return Response.status(206).entity("There is at least one unauthorized sensor!").build();
	}

	@GET
	@Path("/getSex")
	@Produces(MediaType.APPLICATION_XML)
	public String sayHtmlHello() {

		log.info("This is our first log!");

		return "<html> " + "<title>" + "Ronny" + "</title>" + "<body><h1>"
				+ "Ronny" + "</body></h1>" + "</html> ";
	}

}
