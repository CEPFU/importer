package de.fu_berlin.agdb.importer.payload;

import java.sql.Date;

import org.json.JSONObject;

public class ForecastEntry {

	private Date date;
	private double high;
	private double low;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	
	public JSONObject asJSONObject() {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("date", date);
		jsonObject.put("high", high);
		jsonObject.put("low", low);
		
		return jsonObject;
	}
}
