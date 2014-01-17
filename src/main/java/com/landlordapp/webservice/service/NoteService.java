package com.landlordapp.webservice.service;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface NoteService {
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public JSONObject findOne(String id) throws JSONException, IllegalArgumentException, IllegalAccessException;
	
	public JSONObject create(JSONObject note) throws JSONException, IllegalArgumentException, IllegalAccessException;
	
	public JSONObject update(JSONObject note) throws JSONException, IllegalArgumentException, IllegalAccessException;
	
	public void delete(JSONObject jsonNote) throws JSONException, IllegalArgumentException, IllegalAccessException;
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public JSONArray findAll() throws JSONException, IllegalArgumentException, IllegalAccessException;
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public JSONArray findByProperty(Long propertyId) throws JSONException, IllegalArgumentException, IllegalAccessException;
}
