package com.eqtribe.utilities;

import org.json.JSONObject;

/**
 * @author rsadhula
 */
public class DataStore {

	private static JSONObject jsonObject;

	public static JSONObject getJsonObject() {
		return jsonObject;
	}

	public static void setJsonObject(JSONObject jsonObject) {
		DataStore.jsonObject = jsonObject;
	}

	public static JSONObject getJsonObject(String key) {

		return jsonObject.getJSONObject(key);
	}

	public static JSONObject getEnvironmentInfo(String envkey) {
		return jsonObject.getJSONObject("environments").getJSONObject(envkey);
	}

}