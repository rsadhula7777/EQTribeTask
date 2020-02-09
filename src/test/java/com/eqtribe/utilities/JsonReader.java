package com.eqtribe.utilities;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.internal.support.FileReader;

/**
 * @author rsadhula
 */
public class JsonReader {

	private final static String FILEPATH = System.getProperty("user.dir")
			+ "/src/test/java/com/eqtribe/utilities/TestData.Json";

	public static JSONObject getJSONConfigData() {
		try {
			JSONObject json = new JSONObject(FileReader.readToString(new File(FILEPATH), "UTF-8"));
			DataStore.setJsonObject(json);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * retrieve environment info from jsonfile
	 * 
	 * @param envKey
	 * @return JSONObject
	 */
	public static JSONObject getEnvironmentInfo(String envKey) {
		JSONObject json = getJSONConfigData();
		return json.getJSONObject("environments").getJSONObject(envKey);
	}

	public static Object getValue(String key) {
		JSONObject json = getJSONConfigData();
		Object val = json.get(key);
		if (val instanceof JSONObject) {
			return json.getString(key);
		} else if (val instanceof JSONArray) {
			return json.getJSONArray(key);
		}
		return null;

	}
}
