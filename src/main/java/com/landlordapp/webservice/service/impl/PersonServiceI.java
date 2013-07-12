package com.landlordapp.webservice.service.impl;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.landlordapp.webservice.data.PersonDAO;
import com.landlordapp.webservice.domain.Person;
import com.landlordapp.webservice.service.PersonService;

public class PersonServiceI implements PersonService {

	private PersonDAO personDAO;
	
	public JSONObject findOne(String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Person person = personDAO.findById(Long.parseLong(id));
		if(person == null) {
			return null;
		}
		return person.toJSONObject();
	}
	
	public JSONObject create(JSONObject jsonPerson) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Person person = new Person(jsonPerson);
		return personDAO.save(person).toJSONObject();
	}
	
	public JSONObject update(JSONObject jsonPerson) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Person person = new Person(jsonPerson);
		return personDAO.save(person).toJSONObject();
	}

	public void delete(JSONObject jsonPerson) throws JSONException {
		Person person = new Person(jsonPerson);
		personDAO.delete(person);
	}
	
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	public JSONArray findAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Person> persons = this.personDAO.findAll();
		JSONArray personJSONArray = new JSONArray();
		for(Person person: persons) {
			personJSONArray.put(person.toJSONObject());
		}
		return personJSONArray;
	}

}
