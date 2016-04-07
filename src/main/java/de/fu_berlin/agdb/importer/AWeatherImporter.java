package de.fu_berlin.agdb.importer;

import de.fu_berlin.agdb.importer.payload.LocationMetaData;
import de.fu_berlin.agdb.importer.payload.LocationWeatherData;

import java.util.List;

public abstract class AWeatherImporter {
	private long lastTimeLoaded = 0;
	
	public List<LocationWeatherData> getWeatherDataForLocationsRespectingTimeout(List<LocationMetaData> locations){
		List<LocationWeatherData> locationWeatherData = null;
		if(hasTimeoutRanOut()){
			locationWeatherData = getWeatherDataForLocations(locations);
			lastTimeLoaded = System.currentTimeMillis();
		}
		return locationWeatherData;
	}

	private boolean hasTimeoutRanOut() {
		return lastTimeLoaded == 0 || (lastTimeLoaded + getServiceTimeout()) < System.currentTimeMillis();
	}

	protected abstract List<LocationWeatherData> getWeatherDataForLocations(List<LocationMetaData> locations);
	
	protected abstract long getServiceTimeout();
}
