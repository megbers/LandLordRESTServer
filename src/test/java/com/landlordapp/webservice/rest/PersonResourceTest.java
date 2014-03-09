package com.landlordapp.webservice.rest;

import static com.landlordapp.webservice.domain.Person.USER_ID;
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
	private final PersonResource resource = new PersonResource();
	private String id;
	private JSONObject fakePerson;
	private String userId = "this is the user id";

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = "person id";
		fakePerson = new JSONObject();
		fakePerson.put("id", id);
		fakePerson.put(USER_ID, userId);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray persons = new JSONArray();
		when(service.findAll(userId)).thenReturn(persons);
		JSONArray actual = resource.findAllPersons(userId);
		assertEquals(persons, actual);
	}

	@Test
	public void findByPropertyShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Long propertyId = 1L;
		JSONArray persons = new JSONArray();
		when(service.findByProperty(propertyId, userId)).thenReturn(persons);
		JSONArray actual = resource.findPersonByProperty(propertyId, userId);
		assertEquals(persons, actual);
	}

	@Test
	public void findPersonShouldReturnPersonId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(fakePerson);
		JSONObject person = resource.findPerson(id, userId);
		assertEquals(person.get("id"), id);
	}

	@Test
	public void findPersonShouldReturnNullIfNotFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(null);
		JSONObject person = resource.findPerson(id, userId);
		assertNull(person);
	}

	@Test
	public void createPersonShouldReturnPersonId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.create(fakePerson)).thenReturn(fakePerson);
		JSONObject person = resource.createPerson(fakePerson, userId);
		assertEquals(person.get("id"), id);
	}

	@Test
	public void updatePersonPersonShouldReturnPerson() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.update(fakePerson)).thenReturn(fakePerson);
		JSONObject person = resource.updatePerson(fakePerson, userId);
		assertEquals(person.get("id"), id);
	}

	@Test
	public void deletePersonShouldDeletePerson() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(fakePerson);
		JSONObject json = resource.deletePerson(id, userId);
		verify(service).delete(fakePerson);
		assertEquals(json.get("success"), 1);
	}
}
