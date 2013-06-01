package com.landlordapp.webservice.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public interface UserService {
	public JSONObject findOne(String id) throws JSONException;
	
	public JSONObject createUser(JSONObject user) throws JSONException;
	
	public JSONObject updateUser(JSONObject user) throws JSONException;
	
	public void deleteUser(JSONObject jsonUser) throws JSONException;
}
