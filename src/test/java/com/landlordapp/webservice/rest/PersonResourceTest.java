package com.landlordapp.webservice.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.landlordapp.webservice.service.impl.PersonServiceI;

public class PersonResourceTest {
	@Mock
	PersonServiceI service;
	@InjectMocks
	private PersonResource resource = new PersonResource();
	private String id;
	private JSONObject fakePerson;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = "person id";
		fakePerson = new JSONObject();
		fakePerson.put("id", id);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray persons = new JSONArray();
		when(service.findAll()).thenReturn(persons);
		JSONArray actual = resource.findAllPersons();
		assertEquals(persons, actual);
	}
	
	
	@Test
	public void findPersonShouldReturnPersonId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(fakePerson);
		JSONObject person = resource.findPerson(id);
		assertEquals(person.get("id"), id);
	}
	
	@Test
	public void findPersonShouldReturnNullIfNotFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(null);
		JSONObject person = resource.findPerson(id);
		assertNull(person);
	}
	
	@Test
	public void createPersonShouldReturnPersonId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.create(fakePerson)).thenReturn(fakePerson);
		JSONObject person = resource.createPerson(fakePerson);
		assertEquals(person.get("id"), id);
	}
	
	@Test
	public void updatePersonPersonShouldReturnPerson() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.update(fakePerson)).thenReturn(fakePerson);
		JSONObject person = resource.updatePerson(fakePerson);
		assertEquals(person.get("id"), id);
	}
	
	@Test
	public void deletePersonShouldDeletePerson() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(fakePerson);
		JSONObject json = resource.deletePerson(id);
		verify(service).delete(fakePerson);
		assertEquals(json.get("success"), 1);
	}
}
