package de.fu_berlin.agdb.importer.noaa.core;

import de.fu_berlin.agdb.importer.payload.LocationWeatherData;

import java.util.List;

public interface IWorkProvider {

    String getWork();

    void deliverResult(List<LocationWeatherData> dataForLocation);
}
