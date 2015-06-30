package de.fu_berlin.agdb.importer.payload;

import java.sql.Date;

import org.json.JSONObject;

public class LocationWeatherData {

	private StationMetaData stationMetaData;
	private Long timestamp;
	private DataType dataType;
	
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
	private Double high;
	private Double low;

	public LocationWeatherData(StationMetaData stationMetaData, long timestamp, DataType dataType) {
		this.stationMetaData = stationMetaData;
		this.timestamp = timestamp;
		this.dataType = dataType;
	}
	
	public StationMetaData getStationMetaData(){
		return stationMetaData;
	}
	
	public Long getTimestamp() {
		return timestamp;
	}
	
	public DataType getDataType() {
		return dataType;
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
	
	public JSONObject asJSONObject() {

		JSONObject currentEvent = new JSONObject();
		
		currentEvent.put("timestamp", timestamp);
		currentEvent.put("stationMetaData", stationMetaData.asJSONObject());
		currentEvent.put("dataType", dataType);
		
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
		currentEvent.put("heigh", high);
		currentEvent.put("low", low);

		return currentEvent;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}
}
