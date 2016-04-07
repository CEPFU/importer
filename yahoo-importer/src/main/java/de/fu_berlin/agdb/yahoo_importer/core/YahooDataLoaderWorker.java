package de.fu_berlin.agdb.yahoo_importer.core;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.postgis.PGgeometry;

import de.fu_berlin.agdb.importer.payload.DataType;
import de.fu_berlin.agdb.importer.payload.LocationMetaData;
import de.fu_berlin.agdb.importer.payload.LocationWeatherData;

public class YahooDataLoaderWorker implements Runnable{
	private static final Logger logger = LogManager.getLogger(YahooDataLoaderWorker.class);
	
	private YQLRunner yqlRunner;

	private IWorkProvider workProvider;
	
	public YahooDataLoaderWorker(IWorkProvider workProvider) {
		 this.workProvider = workProvider;
		yqlRunner = new YQLRunner();
	}
	
	@Override
	public void run() {
		
		LocationMetaData locationMetaData = null;
		while((locationMetaData = workProvider.getWork()) != null){
			try {
				List<LocationWeatherData> dataForLocation = loadDataForLocation(locationMetaData);
				workProvider.deliverResult(dataForLocation);
			} catch (ParseException | IOException e) {
				logger.error("Error while loading data");
				e.printStackTrace();
			}
		}
	}
	
	private List<LocationWeatherData> loadDataForLocation(LocationMetaData locationMetaData) throws ParseException, ClientProtocolException, IOException{
		ArrayList<LocationWeatherData> locationWeatherDataList = new ArrayList<LocationWeatherData>();
		
		JSONObject weatherForLocation = getWeatherForLocation(locationMetaData.getLocationPosition());
		if(weatherForLocation != null){
			LocationWeatherData locationWeatherData = new LocationWeatherData(locationMetaData, System.currentTimeMillis(), DataType.REPORT);
			
			JSONObject channel = weatherForLocation.getJSONObject("channel");
			
			locationWeatherData.setDate(parseBuildDate(channel.getString("lastBuildDate")));
			
			JSONObject wind = channel.getJSONObject("wind");
			locationWeatherData.setWindChill(JSONHelper.getDouble(wind, "chill"));
			locationWeatherData.setWindDirection(JSONHelper.getDouble(wind, "direction"));
			locationWeatherData.setWindSpeed(JSONHelper.getDouble(wind, "speed"));
			
			JSONObject atmosphere = channel.getJSONObject("atmosphere");
			locationWeatherData.setAtmosphereHumidity(JSONHelper.getDouble(atmosphere, "humidity"));
			locationWeatherData.setAtmospherePressure(JSONHelper.getDouble(atmosphere, "pressure"));
			locationWeatherData.setAtmosphereRising(JSONHelper.getDouble(atmosphere, "rising"));
			locationWeatherData.setAtmosphereVisibility(JSONHelper.getDouble(atmosphere, "visibility"));
			
			JSONObject astronomy = channel.getJSONObject("astronomy");
			locationWeatherData.setAstronomySunrise(astronomy.getString("sunrise"));
			locationWeatherData.setAstronomySunset(astronomy.getString("sunset"));
			
			JSONObject item = channel.getJSONObject("item");
			JSONObject condition = item.getJSONObject("condition");
			locationWeatherData.setTemperature(JSONHelper.getDouble(condition, "temp"));
			
			locationWeatherDataList.add(locationWeatherData);
			
			JSONArray forecast = item.getJSONArray("forecast");
			for(int i = 0; i < forecast.length(); i++){
				JSONObject forecastEntryData = forecast.getJSONObject(i);
				LocationWeatherData forecastWeatherData = new LocationWeatherData(locationMetaData, System.currentTimeMillis(), DataType.FORECAST);
				forecastWeatherData.setDate(parseRFC822Date(forecastEntryData.getString("date")));
				forecastWeatherData.setTemperatureHigh(forecastEntryData.getDouble("high"));
				forecastWeatherData.setTemperatureLow(forecastEntryData.getDouble("low"));
				locationWeatherDataList.add(forecastWeatherData);
			}
			return locationWeatherDataList;
		} else {
			return null;
		}
	}

	private Date parseBuildDate(String date) throws ParseException {
		return parseRFC822Date(date.substring(5, 16));
	}

	private Date parseRFC822Date(String rfc822Date) throws ParseException{
		String datePattern = "dd MMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern, Locale.US);
		return new Date(simpleDateFormat.parse(rfc822Date).getTime());
	}
	
	private JSONObject getWeatherForLocation(PGgeometry location) throws ClientProtocolException, IOException{
		String woeidForLocation = getWoeidForLocation(location);
		if(woeidForLocation != null){
			return getWeatherForWoeid(woeidForLocation);
		} else {
			return null;
		}
	}
	
	private JSONObject getWeatherForWoeid(String woeid) throws IOException{
		String query = "SELECT * FROM weather.forecast WHERE woeid = " 
				+ woeid 
				+ "  AND u = 'c'";
		return yqlRunner.getYQLResponseJSON(query);
	}
	
	private String getWoeidForLocation(PGgeometry location) throws ClientProtocolException, IOException{
		try {
			String query = "SELECT woeid FROM geo.placefinder WHERE text='" 
					+ location.getGeometry().getFirstPoint().getY()
					+ "," 
					+ location.getGeometry().getFirstPoint().getX()
					+ "' AND gflags = 'R'";
			logger.debug(query);
			return yqlRunner.getYQLResponseJSON(query).getJSONObject("Result").getString("woeid");
		}
		catch (JSONException e){
			logger.debug("No woeid could be foud for the location with coordinates:" 
					+ location.getGeometry().getFirstPoint().getY() 
					+ " : "
					+ location.getGeometry().getFirstPoint().getX()
					, e);
			return null;
		}
	}
}
