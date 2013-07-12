package com.landlordapp.webservice.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.data.PersonDAO;
import com.landlordapp.webservice.data.PropertyDAO;
import com.landlordapp.webservice.domain.Person;
import com.landlordapp.webservice.domain.Property;
import com.landlordapp.webservice.service.PropertyService;

public class PropertyServiceI implements PropertyService {

	private PropertyDAO propertyDAO;
	private PersonDAO personDAO;
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public JSONObject findOne(String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Property property = propertyDAO.findById(Long.parseLong(id));
		if(property == null) {
			return null;
		}
		return property.toJSONObject();
	}
	
	public JSONObject create(JSONObject jsonProperty) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Property property = new Property(jsonProperty);
		addTenants(jsonProperty, property);
		return propertyDAO.save(property).toJSONObject();
	}

	private void addTenants(JSONObject jsonProperty, Property property) {
		Person person;
		try {
			person = personDAO.findById(jsonProperty.getLong("tenant"));
			Set<Person> tenants = new HashSet<Person>();
			tenants.add(person);
			person.setProperty(property);
			property.setTenants(tenants);
		} catch (JSONException e) { }
	}
	
	public JSONObject update(JSONObject jsonProperty) throws JSONException, IllegalArgumentException, IllegalAccessException {
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
	
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public JSONArray findAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Property> propertys = this.propertyDAO.findAll();
		JSONArray propertyJSONArray = new JSONArray();
		for(Property property: propertys) {
			propertyJSONArray.put(property.toJSONObject());
		}
		return propertyJSONArray;
	}

}
