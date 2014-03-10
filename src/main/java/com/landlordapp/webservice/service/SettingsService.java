package com.landlordapp.webservice.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface SettingsService {
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public JSONObject findOne(Long id, String userId) throws JSONException, IllegalArgumentException, IllegalAccessException;
	
	public JSONObject create(JSONObject expense) throws JSONException, IllegalArgumentException, IllegalAccessException;
	
	public JSONObject update(JSONObject expense) throws JSONException, IllegalArgumentException, IllegalAccessException;
}
