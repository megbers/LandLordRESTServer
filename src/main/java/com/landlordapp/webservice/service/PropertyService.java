package com.landlordapp.webservice.service;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public interface PropertyService {
	public JSONObject findOne(String id) throws JSONException;
	
	public JSONObject create(JSONObject property) throws JSONException;
	
	public JSONObject update(JSONObject property) throws JSONException;
	
	public void delete(JSONObject jsonProperty) throws JSONException;
	
	public JSONArray findAll() throws JSONException;
}
