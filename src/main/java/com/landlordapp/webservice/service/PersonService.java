package com.landlordapp.webservice.service;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface PersonService {
	public JSONObject findOne(Long id, String userId) throws JSONException, IllegalArgumentException, IllegalAccessException;

	public JSONObject create(JSONObject property) throws JSONException, IllegalArgumentException, IllegalAccessException;

	public JSONObject update(JSONObject property) throws JSONException, IllegalArgumentException, IllegalAccessException;

	public void delete(JSONObject jsonProperty) throws JSONException, IllegalArgumentException, IllegalAccessException;

	public JSONArray findAll(String userId) throws JSONException, IllegalArgumentException, IllegalAccessException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public JSONArray findByProperty(Long propertyId, String userId) throws JSONException, IllegalArgumentException, IllegalAccessException;
}
