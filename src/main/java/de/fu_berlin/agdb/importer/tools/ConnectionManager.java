package de.fu_berlin.agdb.importer.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.postgis.PGgeometry;

public class ConnectionManager {
	private static final Logger logger = LogManager.getLogger(ConnectionManager.class);
	
	private String host;
	private String database;
	private String user;
	private String password;

	private int maxConnections;
	
	private ArrayList<Connection> freeConnections;
	private ArrayList<Connection> usedConnections;
	
	private boolean poolIsShutDown;
	
	public ConnectionManager(String host, String database, String user, String password, int maxConnections) {
		this.host = host;
		this.database = database;
		this.user = user;
		this.password = password;
		this.maxConnections = maxConnections;
		
		freeConnections = new ArrayList<Connection>();
		usedConnections = new ArrayList<Connection>();
		
		poolIsShutDown = false;
	}
	
	private Properties getDatabaseProperties(){
		Properties properties = new Properties();
		properties.setProperty("user", user);
		properties.setProperty("password", password);
		return properties;
	}
	
	public synchronized Connection requestConnection() throws Exception{
		while(true && !poolIsShutDown){
			if(freeConnections.size() > 0){
				Connection connection = freeConnections.get(0);
				freeConnections.remove(0);
				usedConnections.add(connection);
				return connection;
			} else if (usedConnections.size() < maxConnections){
				Connection connection = DriverManager.getConnection("jdbc:postgresql://" + host + "/" + database, getDatabaseProperties());
				((org.postgresql.PGConnection)connection).addDataType("geometry",PGgeometry.class);
				usedConnections.add(connection);
				return connection;
			}
			try {
				this.wait();
			} catch (InterruptedException e) {
				logger.error("Interrupted while waiting for Lock:", e);
			}
		}
		throw new Exception("This ConnectionManager has been shut down.");
	}
	
	public void returnConnectionToPool(Connection connection){
		synchronized (this) {
			usedConnections.remove(connection);
			freeConnections.add(connection);
			this.notify();
		}
	}
	
	public void shutDownPool() throws SQLException{
		synchronized (this) {
			for (Connection connection : freeConnections) {
				connection.close();
			}
			for (Connection connection : usedConnections) {
				connection.close();
			}
			poolIsShutDown = true;
			this.notifyAll();
		}
	}
}