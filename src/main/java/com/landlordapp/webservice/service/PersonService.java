package com.landlordapp.webservice.service;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public interface PersonService {
	public JSONObject findOne(String id) throws JSONException, IllegalArgumentException, IllegalAccessException;
	
	public JSONObject create(JSONObject property) throws JSONException, IllegalArgumentException, IllegalAccessException;
	
	public JSONObject update(JSONObject property) throws JSONException, IllegalArgumentException, IllegalAccessException;
	
	public void delete(JSONObject jsonProperty) throws JSONException, IllegalArgumentException, IllegalAccessException;
	
	public JSONArray findAll() throws JSONException, IllegalArgumentException, IllegalAccessException;
}
