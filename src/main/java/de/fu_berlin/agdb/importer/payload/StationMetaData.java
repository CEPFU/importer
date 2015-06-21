package de.fu_berlin.agdb.importer.payload;

import java.sql.Date;

import org.json.JSONObject;
import org.postgis.PGgeometry;

public class StationMetaData {

	private long stationId;
	private PGgeometry stationPosition;
	private Date fromDate;
	private Date untilDate;
	private int stationHeight;
	private String stationName;
	private String federalState;
	
	public long getStationId() {
		return stationId;
	}
	public void setStationId(long stationId) {
		this.stationId = stationId;
	}
	public PGgeometry getStationPosition() {
		return stationPosition;
	}
	public void setStationPosition(PGgeometry stationPosition) {
		this.stationPosition = stationPosition;
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
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getFederalState() {
		return federalState;
	}
	public void setFederalState(String federalState) {
		this.federalState = federalState;
	}
	
	public JSONObject asJSONObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("stationId", stationId);
		jsonObject.put("stationPosition", stationPosition);
		jsonObject.put("fromDate", fromDate);
		jsonObject.put("stationHeight", stationHeight);
		jsonObject.put("stationName", stationName);
		jsonObject.put("federalState", federalState);
		return jsonObject;
	}

}
