package de.fu_berlin.agdb.importer.tools;

import de.fu_berlin.agdb.importer.payload.LocationMetaData;
import de.fu_berlin.agdb.importer.payload.StationMetaData;
import org.postgis.PGgeometry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationLoader {
	private ConnectionManager connectionManager;

	public LocationLoader(String host, String database, String user, String password) {
		connectionManager = new ConnectionManager(host, database, user, password, 10);
	}
	
	public synchronized List<LocationMetaData> getLocations() throws Exception{
		ArrayList<LocationMetaData> stations = new ArrayList<LocationMetaData>();
		
		Connection connection = connectionManager.requestConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement(""
				+ "SELECT * "
				+ "FROM location_meta_data, dwd_meta_data "
				+ "WHERE location_meta_data.location_id = dwd_meta_data.location_id "
				+ ";");
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			StationMetaData stationMetaData = getStationMetaDataFromResultSet(resultSet);
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
		
		PreparedStatement preparedStatement = connection.prepareStatement(""
				+ "SELECT * "
				+ "FROM location_meta_data, dwd_meta_data "
				+ "WHERE location_meta_data.location_id = dwd_meta_data.location_id "
				+ "AND station_id = ? "
				+ ";");
		preparedStatement.setLong(1, stationId);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()){
			stationMetaData = getStationMetaDataFromResultSet(resultSet);
		}
		resultSet.close();
		preparedStatement.close();
		connectionManager.returnConnectionToPool(connection);
		
		return stationMetaData;
	}

	private StationMetaData getStationMetaDataFromResultSet(ResultSet resultSet) throws SQLException {
		StationMetaData stationMetaData;
		stationMetaData = new StationMetaData();
		
		stationMetaData.setLocationId(resultSet.getLong("location_id"));
		stationMetaData.setLocationPosition((PGgeometry) resultSet.getObject("location_position"));
		stationMetaData.setLocationDescription(resultSet.getString("location_description"));
		
		stationMetaData.setStationId(resultSet.getLong("station_id"));
		stationMetaData.setFromDate(resultSet.getDate("from_date"));
		stationMetaData.setUntilDate(resultSet.getDate("until_date"));
		stationMetaData.setStationHeight(resultSet.getInt("station_height"));
		stationMetaData.setFederalState(resultSet.getString("federal_state"));
		return stationMetaData;
	}
}
