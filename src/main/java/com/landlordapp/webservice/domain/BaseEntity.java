package com.landlordapp.webservice.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public abstract class BaseEntity {
	public String getString(JSONObject json, String key) {
		try {
			return json.getString(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	public Long getLong(JSONObject json, String key) {
		try {
			return json.getLong(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	public Double getDouble(JSONObject json, String key) {
		try {
			return json.getDouble(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	public Boolean getBoolean(JSONObject json, String key) {
		try {
			return json.getBoolean(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	public Date getDate(JSONObject json, String key) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			return sdf.parse(json.getString(key));
		} catch (Exception e) {
			return null;
		}
	}
	
	protected String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	public abstract JSONObject toJSONObject() throws JSONException;
}
