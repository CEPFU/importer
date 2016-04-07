package de.fu_berlin.agdb.importer.yahoo.core;

import org.json.JSONException;
import org.json.JSONObject;
 
public final class JSONHelper {
	
	public static Double getDouble(JSONObject object, String key){
		try {
			return object.getDouble(key);
		} catch (JSONException e) {
			return null;
		}
	}
}
