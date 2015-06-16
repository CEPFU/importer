package de.fu_berlin.agdb.yahoo_importer;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import de.fu_berlin.agdb.importer.AWeatherImporter;
import de.fu_berlin.agdb.importer.payload.LocationWeatherData;
import de.fu_berlin.agdb.importer.payload.StationMetaData;
import de.fu_berlin.agdb.yahoo_importer.core.YahooDataLoader;

public class YahooImporter extends AWeatherImporter{
	private static final Logger logger = LogManager.getLogger(YahooImporter.class);
	
	private YahooDataLoader yahooDataLoader;
	
	public YahooImporter() {
		yahooDataLoader = new YahooDataLoader();
	}

	@Override
	protected List<LocationWeatherData> getWeatherDataForLocations(List<StationMetaData> locations) {
		List<LocationWeatherData> locationWeatherDatas = new ArrayList<LocationWeatherData>();
		
		for (StationMetaData stationMetaData : locations) {
			try {
				
				LocationWeatherData dataForLocation = yahooDataLoader.loadDataForLocation(stationMetaData);
				if(dataForLocation != null){
					locationWeatherDatas.add(dataForLocation);
				}
			} catch (Exception e) {
				logger.error("An Exception was caught while running the YahooImporter:", e);
			}
		}
		
		return locationWeatherDatas;
	}

	@Override
	protected long getServiceTimeout() {
		//5 minutes
		return 5*60*1000;
	}

}
