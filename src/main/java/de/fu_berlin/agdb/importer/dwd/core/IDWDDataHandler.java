package de.fu_berlin.agdb.importer.dwd.core;

import de.fu_berlin.agdb.importer.payload.LocationWeatherData;
import de.fu_berlin.agdb.importer.payload.StationMetaData;

public interface IDWDDataHandler {
	public void addData(LocationWeatherData locationWeatherData);
	
	public StationMetaData getMetaDataForStation(long id);
}
