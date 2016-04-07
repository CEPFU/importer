package de.fu_berlin.agdb.openweather_importer.core;

/**
 * Created by riva on 03.01.16.
 */

import de.fu_berlin.agdb.importer.payload.LocationMetaData;
import de.fu_berlin.agdb.importer.payload.LocationWeatherData;

import java.util.List;

public interface IWorkProvider {

    LocationMetaData getWork();

    void deliverResult(List<LocationWeatherData> dataForLocation);

}
