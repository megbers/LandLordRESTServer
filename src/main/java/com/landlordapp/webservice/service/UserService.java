package com.landlordapp.webservice.service;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public interface UserService {
	public JSONObject findOne(String id) throws JSONException;
	
	public JSONObject create(JSONObject user) throws JSONException;
	
	public JSONObject update(JSONObject user) throws JSONException;
	
	public void delete(JSONObject jsonUser) throws JSONException;
	
	public JSONArray findAll() throws JSONException;
}
