package de.fu_berlin.agdb.importer.payload;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class LocationWeatherData {

	private StationMetaData stationMetaData;
	
	private Date date;
	private double windChill;
	private double windDirection;
	private double windSpeed;
	private double atmosphereHumidity;
	private double atmospherePressure;
	private double atmosphereRising;
	private double atmosphereVisibility;
	private String astronomySunrise;
	private String astronomySunset;
	private double temperature;

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
	
	public double getWindChill() {
		return windChill;
	}
	
	public void setWindChill(double windChill) {
		this.windChill = windChill;
	}
	
	public double getWindDirection() {
		return windDirection;
	}
	
	public void setWindDirection(double windDirection) {
		this.windDirection = windDirection;
	}
	
	public double getWindSpeed() {
		return windSpeed;
	}
	
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	public double getAtmosphereHumidity() {
		return atmosphereHumidity;
	}
	
	public void setAtmosphereHumidity(double atmosphereHumidity) {
		this.atmosphereHumidity = atmosphereHumidity;
	}
	
	public double getAtmospherePressure() {
		return atmospherePressure;
	}
	
	public void setAtmospherePressure(double atmospherePressure) {
		this.atmospherePressure = atmospherePressure;
	}
	
	public double getAtmosphereRising() {
		return atmosphereRising;
	}
	
	public void setAtmosphereRising(double atmosphereRising) {
		this.atmosphereRising = atmosphereRising;
	}
	
	public double getAtmosphereVisibility() {
		return atmosphereVisibility;
	}
	
	public void setAtmosphereVisibility(double atmosphereVisibility) {
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
	
	public double getTemperature() {
		return temperature;
	}
	
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public List<ForecastEntry> getForecastEntrys() {
		return forecastEntrys;
	}
	
	public void addForecastEntry(ForecastEntry forecastEntry) {
		this.forecastEntrys.add(forecastEntry);
	}
}
