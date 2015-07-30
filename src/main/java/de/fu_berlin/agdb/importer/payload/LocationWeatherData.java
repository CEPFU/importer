package de.fu_berlin.agdb.importer.payload;

import java.sql.Date;

import org.json.JSONObject;

public class LocationWeatherData {

	private LocationMetaData locationMetaData;
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
	private Double temperatureHigh;
	private Double temperatureLow;
	
	private Integer qualityLevel;
	private Double steamPressure;
	private Double cloudage;
	private Double minimumAirGroundTemperature;
	private Double maximumWindSpeed;
	private Double precipitationDepth;
	private Double sunshineDuration;
	private Double snowHeight;

	public LocationWeatherData(LocationMetaData locationMetaData, long timestamp, DataType dataType) {
		this.locationMetaData = locationMetaData;
		this.timestamp = timestamp;
		this.dataType = dataType;
	}
	
	public LocationMetaData getLocationMetaData(){
		return locationMetaData;
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

	public Double getTemperatureHigh() {
		return temperatureHigh;
	}

	public void setTemperatureHigh(Double temperatureHigh) {
		this.temperatureHigh = temperatureHigh;
	}

	public Double getTemperatureLow() {
		return temperatureLow;
	}

	public void setTemperatureLow(Double temperatureLow) {
		this.temperatureLow = temperatureLow;
	}

	public Integer getQualityLevel() {
		return qualityLevel;
	}

	public void setQualityLevel(Integer qualityLevel) {
		this.qualityLevel = qualityLevel;
	}

	public Double getSteamPressure() {
		return steamPressure;
	}

	public void setSteamPressure(Double steamPressure) {
		this.steamPressure = steamPressure;
	}

	public Double getCloudage() {
		return cloudage;
	}

	public void setCloudage(Double cloudage) {
		this.cloudage = cloudage;
	}

	public Double getMinimumAirGroundTemperature() {
		return minimumAirGroundTemperature;
	}

	public void setMinimumAirGroundTemperature(Double minimumAirGroundTemperature) {
		this.minimumAirGroundTemperature = minimumAirGroundTemperature;
	}

	public Double getMaximumWindSpeed() {
		return maximumWindSpeed;
	}

	public void setMaximumWindSpeed(Double maximumWindSpeed) {
		this.maximumWindSpeed = maximumWindSpeed;
	}

	public Double getPrecipitationDepth() {
		return precipitationDepth;
	}

	public void setPrecipitationDepth(Double precipitationDepth) {
		this.precipitationDepth = precipitationDepth;
	}

	public Double getSunshineDuration() {
		return sunshineDuration;
	}

	public void setSunshineDuration(Double sunshineDuration) {
		this.sunshineDuration = sunshineDuration;
	}

	public Double getSnowHeight() {
		return snowHeight;
	}

	public void setSnowHeight(Double snowHeight) {
		this.snowHeight = snowHeight;
	}
	
	
	public JSONObject asJSONObject() {

		JSONObject currentEvent = new JSONObject();
		
		currentEvent.put("timestamp", timestamp);
		currentEvent.put("stationMetaData", locationMetaData.asJSONObject());
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
		currentEvent.put("temperatureHigh", temperatureHigh);
		currentEvent.put("temperatureLow", temperatureLow);
		
		currentEvent.put("qualityLevel", qualityLevel);
		currentEvent.put("steamPressure", steamPressure);
		currentEvent.put("cloudage", cloudage);
		currentEvent.put("minimumAirGroundTemperature", minimumAirGroundTemperature);
		currentEvent.put("maximumWindSpeed", maximumWindSpeed);
		currentEvent.put("precipitationDepth", precipitationDepth);
		currentEvent.put("sunshineDuration", sunshineDuration);
		currentEvent.put("snowHeight", snowHeight);

		return currentEvent;
	}
}
