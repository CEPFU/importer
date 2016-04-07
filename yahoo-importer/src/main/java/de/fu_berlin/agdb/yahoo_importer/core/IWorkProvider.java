package de.fu_berlin.agdb.yahoo_importer.core;

import java.util.List;

import de.fu_berlin.agdb.importer.payload.LocationMetaData;
import de.fu_berlin.agdb.importer.payload.LocationWeatherData;

public interface IWorkProvider {

	LocationMetaData getWork();

	void deliverResult(List<LocationWeatherData> dataForLocation);

}
