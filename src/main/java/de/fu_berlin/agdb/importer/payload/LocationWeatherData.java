package de.fu_berlin.agdb.importer.payload;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class LocationWeatherData {

	private StationMetaData stationMetaData;
	
	private Date date;
	private Double windChill;
	private Double windDirection;
	private Double windSpeed;
	private Double atmosphereHumidity;
	private Double atmospherePressure;
	private Double atmosphereRising;
	private Double atmosphereVisibility;
	private String astronomySunrise;
	private String astronomySunset;
	private Double temperature;

	private List<ForecastEntry> forecastEntrys;
	
	public LocationWeatherData(StationMetaData stationMetaData) {
		this.stationMetaData = stationMetaData;
		forecastEntrys = new ArrayList<ForecastEntry>();
	}
	
	public StationMetaData getStationMetaData(){
		return stationMetaData;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Double getWindChill() {
		return windChill;
	}
	
	public void setWindChill(Double windChill) {
		this.windChill = windChill;
	}
	
	public Double getWindDirection() {
		return windDirection;
	}
	
	public void setWindDirection(Double windDirection) {
		this.windDirection = windDirection;
	}
	
	public Double getWindSpeed() {
		return windSpeed;
	}
	
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	public Double getAtmosphereHumidity() {
		return atmosphereHumidity;
	}
	
	public void setAtmosphereHumidity(Double atmosphereHumidity) {
		this.atmosphereHumidity = atmosphereHumidity;
	}
	
	public Double getAtmospherePressure() {
		return atmospherePressure;
	}
	
	public void setAtmospherePressure(Double atmospherePressure) {
		this.atmospherePressure = atmospherePressure;
	}
	
	public Double getAtmosphereRising() {
		return atmosphereRising;
	}
	
	public void setAtmosphereRising(Double atmosphereRising) {
		this.atmosphereRising = atmosphereRising;
	}
	
	public Double getAtmosphereVisibility() {
		return atmosphereVisibility;
	}
	
	public void setAtmosphereVisibility(Double atmosphereVisibility) {
		this.atmosphereVisibility = atmosphereVisibility;
	}
	
	public String getAstronomySunrise() {
		return astronomySunrise;
	}
	
	public void setAstronomySunrise(String astronomySunrise) {
		this.astronomySunrise = astronomySunrise;
	}
	
	public String getAstronomySunset() {
		return astronomySunset;
	}
	
	public void setAstronomySunset(String astronomySunset) {
		this.astronomySunset = astronomySunset;
	}
	
	public Double getTemperature() {
		return temperature;
	}
	
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	
	public List<ForecastEntry> getForecastEntrys() {
		return forecastEntrys;
	}
	
	public void addForecastEntry(ForecastEntry forecastEntry) {
		this.forecastEntrys.add(forecastEntry);
	}

	public JSONObject asJSONObject() {

		JSONObject currentEvent = new JSONObject();
		
		currentEvent.put("stationMetaData", stationMetaData.asJSONObject());
		
		currentEvent.put("date", date);
		currentEvent.put("windChill", windChill);
		currentEvent.put("windDirection", windDirection);
		currentEvent.put("windSpeed", windSpeed);
		currentEvent.put("atmosphereHumidity", atmosphereHumidity);
		currentEvent.put("atmospherePressure", atmospherePressure);
		currentEvent.put("atmosphereRising", atmosphereRising);
		currentEvent.put("atmosphereVisibility", atmosphereVisibility);
		currentEvent.put("astronomySunrise", astronomySunrise);
		currentEvent.put("astronomySunset", astronomySunset);
		currentEvent.put("temperature", temperature);
		
		JSONArray jsonForecastEntrys = new JSONArray();
		for(int i = 0; i < forecastEntrys.size(); i++){
			jsonForecastEntrys.put(forecastEntrys.get(i).asJSONObject());
		}
		currentEvent.put("forecastEntrys", jsonForecastEntrys);
		
		return currentEvent;
	}
}
