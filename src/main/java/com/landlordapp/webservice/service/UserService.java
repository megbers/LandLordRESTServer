package com.landlordapp.webservice.service;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public interface UserService {
	public JSONObject findOne(Long id) throws JSONException, IllegalArgumentException, IllegalAccessException;

	public JSONObject create(JSONObject user) throws JSONException, IllegalArgumentException, IllegalAccessException;

	public JSONObject update(JSONObject user) throws JSONException, IllegalArgumentException, IllegalAccessException;

	public void delete(JSONObject jsonUser) throws JSONException, IllegalArgumentException, IllegalAccessException;

	public JSONArray findAll() throws JSONException, IllegalArgumentException, IllegalAccessException;

	public JSONObject login(JSONObject jsonUser) throws JSONException;
}
