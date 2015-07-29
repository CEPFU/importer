package de.fu_berlin.agdb.importer.payload;

import java.sql.Date;

import org.json.JSONObject;

public class StationMetaData extends LocationMetaData {

	private long stationId;
	private Date fromDate;
	private Date untilDate;
	private int stationHeight;
	private String federalState;
	
	public long getStationId() {
		return stationId;
	}
	
	public void setStationId(long stationId) {
		this.stationId = stationId;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	public Date getUntilDate() {
		return untilDate;
	}
	
	public void setUntilDate(Date untilDate) {
		this.untilDate = untilDate;
	}
	
	public int getStationHeight() {
		return stationHeight;
	}
	
	public void setStationHeight(int stationHeight) {
		this.stationHeight = stationHeight;
	}
	
	public String getFederalState() {
		return federalState;
	}
	
	public void setFederalState(String federalState) {
		this.federalState = federalState;
	}
	
	public JSONObject asJSONObject() {
		JSONObject jsonObject = super.asJSONObject();
		jsonObject.put("stationId", stationId);
		jsonObject.put("fromDate", fromDate);
		jsonObject.put("stationHeight", stationHeight);
		jsonObject.put("federalState", federalState);
		return jsonObject;
	}

}
