package de.fu_berlin.agdb.importer;

import java.util.List;

import de.fu_berlin.agdb.importer.payload.LocationWeatherData;
import de.fu_berlin.agdb.importer.payload.StationMetaData;


public abstract class AWeatherImporter {
	private long lastTimeLoaded = 0;
	
	public List<LocationWeatherData> getWeatherDataForLocationsRespectingTimeout(List<StationMetaData> locations){
		List<LocationWeatherData> locationWeatherData = null;
		if(hasTimeoutRanOut()){
			locationWeatherData = getWeatherDataForLocations(locations);
			lastTimeLoaded = System.currentTimeMillis();
		}
		return locationWeatherData;
	}

	private boolean hasTimeoutRanOut() {
		return lastTimeLoaded == 0 || (lastTimeLoaded + getServiceTimeout()) > System.currentTimeMillis();
	}

	protected abstract List<LocationWeatherData> getWeatherDataForLocations(List<StationMetaData> locations);
	
	protected abstract long getServiceTimeout();
}
