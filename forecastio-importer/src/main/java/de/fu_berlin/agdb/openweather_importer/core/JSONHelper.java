package de.fu_berlin.agdb.openweather_importer.core;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by riva on 03.01.16.
 */
public final class JSONHelper {

    public static Double getDouble(JSONObject object, String key) {
        try {
            return object.getDouble(key);
        } catch (JSONException e) {
            return null;
        }
    }
}
