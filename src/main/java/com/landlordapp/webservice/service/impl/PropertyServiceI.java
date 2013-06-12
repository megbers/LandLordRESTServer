package com.landlordapp.webservice.service.impl;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.landlordapp.webservice.data.PropertyDAO;
import com.landlordapp.webservice.domain.Property;
import com.landlordapp.webservice.service.PropertyService;

public class PropertyServiceI implements PropertyService {

	private PropertyDAO propertyDAO;
	
	public JSONObject findOne(String id) throws JSONException {
		Property property = propertyDAO.findById(Long.parseLong(id));
		if(property == null) {
			return null;
		}
		return property.toJSONObject();
	}
	
	public JSONObject create(JSONObject jsonProperty) throws JSONException {
		Property property = new Property(jsonProperty);
		return propertyDAO.save(property).toJSONObject();
	}
	
	public JSONObject update(JSONObject jsonProperty) throws JSONException {
		Property property = new Property(jsonProperty);
		return propertyDAO.save(property).toJSONObject();
	}

	public void delete(JSONObject jsonProperty) throws JSONException {
		Property property = new Property(jsonProperty);
		propertyDAO.delete(property);
	}
	
	public void setPropertyDAO(PropertyDAO propertyDAO) {
		this.propertyDAO = propertyDAO;
	}
	
	public JSONArray findAll() throws JSONException {
		List<Property> propertys = this.propertyDAO.findAll();
		JSONArray propertyJSONArray = new JSONArray();
		for(Property property: propertys) {
			propertyJSONArray.put(property.toJSONObject());
		}
		return propertyJSONArray;
	}

}
