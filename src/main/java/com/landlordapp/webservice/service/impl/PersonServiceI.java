package com.landlordapp.webservice.service.impl;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.data.PersonDAO;
import com.landlordapp.webservice.domain.Person;
import com.landlordapp.webservice.service.PersonService;

public class PersonServiceI implements PersonService {

	private PersonDAO personDAO;

	public JSONObject findOne(final String id, String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Person person = personDAO.findById(Long.parseLong(id), userId);
		if (person == null) {
			return null;
		}
		return person.toJSONObject();
	}

	public JSONObject create(final JSONObject jsonPerson) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Person person = new Person(jsonPerson);
		return personDAO.save(person).toJSONObject();
	}

	public JSONObject update(final JSONObject jsonPerson) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Person person = new Person(jsonPerson);
		return personDAO.save(person).toJSONObject();
	}

	public void delete(final JSONObject jsonPerson) throws JSONException {
		Person person = new Person(jsonPerson);
		personDAO.delete(person);
	}

	public void setPersonDAO(final PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public JSONArray findAll(String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Person> persons = this.personDAO.findAll(userId);
		JSONArray personJSONArray = new JSONArray();
		for (Person person : persons) {
			personJSONArray.put(person.toJSONObject());
		}
		return personJSONArray;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public JSONArray findByProperty(final Long propertyId, String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Person> persons = this.personDAO.findByProperty(propertyId, userId);
		JSONArray personJSONArray = new JSONArray();
		for (Person person : persons) {
			personJSONArray.put(person.toJSONObject());
		}
		return personJSONArray;
	}

}
