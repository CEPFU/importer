package de.fu_berlin.agdb.importer.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.postgis.PGgeometry;

import de.fu_berlin.agdb.importer.payload.StationMetaData;

public class LocationLoader {
	private ConnectionManager connectionManager;

	public LocationLoader(String host, String database, String user, String password) {
		connectionManager = new ConnectionManager(host, database, user, password, 10);
	}
	
	public synchronized List<StationMetaData> getLocations() throws Exception{
		ArrayList<StationMetaData> stations = new ArrayList<StationMetaData>();
		
		Connection connection = connectionManager.requestConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM location_meta_data, dwd_meta_data WHERE location_meta_data.location_id = dwd_meta_data.location_id");
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			StationMetaData stationMetaData = new StationMetaData();
			
			stationMetaData.setStationId(resultSet.getLong("station_id"));
			stationMetaData.setStationPosition((PGgeometry) resultSet.getObject("station_position"));
			stationMetaData.setFromDate(resultSet.getDate("from_date"));
			stationMetaData.setUntilDate(resultSet.getDate("until_date"));
			stationMetaData.setStationHeight(resultSet.getInt("station_height"));
			stationMetaData.setStationName(resultSet.getString("station_name"));
			stationMetaData.setFederalState(resultSet.getString("federal_state"));
			
			stations.add(stationMetaData);
		}
		resultSet.close();
		preparedStatement.close();
		connectionManager.returnConnectionToPool(connection);
		return stations;
	}
	
	public synchronized StationMetaData getLocation(Long stationId) throws Exception{
		Connection connection = connectionManager.requestConnection();
		StationMetaData stationMetaData = null;
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dwd_station_meta_data WHERE station_id = ?");
		preparedStatement.setLong(1, stationId);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()){
			stationMetaData = new StationMetaData();
			
			stationMetaData.setStationId(resultSet.getLong("station_id"));
			stationMetaData.setStationPosition((PGgeometry) resultSet.getObject("station_position"));
			stationMetaData.setFromDate(resultSet.getDate("from_date"));
			stationMetaData.setUntilDate(resultSet.getDate("until_date"));
			stationMetaData.setStationHeight(resultSet.getInt("station_height"));
			stationMetaData.setStationName(resultSet.getString("station_name"));
			stationMetaData.setFederalState(resultSet.getString("federal_state"));
		}
		resultSet.close();
		preparedStatement.close();
		connectionManager.returnConnectionToPool(connection);
		
		return stationMetaData;
	}
}
