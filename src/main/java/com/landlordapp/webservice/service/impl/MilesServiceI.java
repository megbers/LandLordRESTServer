package com.landlordapp.webservice.service.impl;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.data.MilesDAO;
import com.landlordapp.webservice.domain.Miles;
import com.landlordapp.webservice.service.MilesService;

public class MilesServiceI implements MilesService{
	private MilesDAO milesDAO;
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public JSONObject findOne(String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Miles miles = milesDAO.findById(Long.parseLong(id));
		if(miles == null) {
			return null;
		}
		return miles.toJSONObject();
	}
	
	public JSONObject create(JSONObject jsonMiles) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Miles miles = new Miles(jsonMiles);
		return milesDAO.save(miles).toJSONObject();
	}

	public JSONObject update(JSONObject jsonMiles) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Miles miles = new Miles(jsonMiles);
		return milesDAO.save(miles).toJSONObject();
	}

	public void delete(JSONObject jsonMiles) throws JSONException {
		Miles miles = new Miles(jsonMiles);
		milesDAO.delete(miles);
	}
	
	public void setMilesDAO(MilesDAO MilesDAO) {
		this.milesDAO = MilesDAO;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public JSONArray findAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Miles> milesList = this.milesDAO.findAll();
		JSONArray milesJSONArray = new JSONArray();
		for(Miles miles: milesList) {
			milesJSONArray.put(miles.toJSONObject());
		}
		return milesJSONArray;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public JSONArray findByProperty(Long propertyId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Miles> milesList = this.milesDAO.findByProperty(propertyId);
		JSONArray milesJSONArray = new JSONArray();
		for(Miles miles: milesList) {
			milesJSONArray.put(miles.toJSONObject());
		}
		return milesJSONArray;
	}
}
