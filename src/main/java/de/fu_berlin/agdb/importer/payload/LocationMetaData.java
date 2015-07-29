package de.fu_berlin.agdb.importer.payload;

import org.json.JSONObject;
import org.postgis.PGgeometry;

public class LocationMetaData {
	
	private Long locationId;
	private String locationDescription;
	private PGgeometry locationPosition;
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	public String getLocationDescription() {
		return locationDescription;
	}
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
	public PGgeometry getLocationPosition() {
		return locationPosition;
	}
	public void setLocationPosition(PGgeometry locationPosition) {
		this.locationPosition = locationPosition;
	}
	
	public JSONObject asJSONObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("locationId", locationId);
		jsonObject.put("locationDescription", locationDescription);
		jsonObject.put("locationPosition", locationPosition);
		return jsonObject;
	}
}
