package com.landlordapp.webservice.service.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.landlordapp.webservice.data.PersonDAO;
import com.landlordapp.webservice.domain.Person;
import com.landlordapp.webservice.domain.type.PersonType;

public class PersonServiceITest {
	@Mock
	private PersonDAO personDAO;
	@InjectMocks
	private PersonServiceI service;
	private String idString = "1001";
	private Long id = 1001L;
	private Person person;
	private JSONObject jsonPerson;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		service = new PersonServiceI();
		person = new Person();
		jsonPerson = new JSONObject();
		jsonPerson.put("id", idString);
		jsonPerson.put("email", "email");
		jsonPerson.put("phone", "phone");
		jsonPerson.put("name", "name");
		jsonPerson.put("type", PersonType.TENANT);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findOneShouldReturnPersonWhenOneIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		person.setId(id);
		when(personDAO.findById(id)).thenReturn(person);
		
		JSONObject object = service.findOne(idString);
		assertEquals(object.get("id"), id);
	}
	
	@Test
	public void findOneShouldReturnNullWhenNoPersonIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(personDAO.findById(id)).thenReturn(null);
		
		JSONObject object = service.findOne(idString);
		assertNull(object);
	}
	
	@Test
	public void createPersonShouldCallSavePerson() throws JSONException, IllegalArgumentException, IllegalAccessException {
		person.setId(id);
		
		when(personDAO.save(any(Person.class))).thenReturn(person);
		JSONObject actual = service.create(jsonPerson);
		
		assertEquals(actual.get("id"), id);
	}
	
	@Test
	public void findAllShouldCallFindAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Person> list = new ArrayList<Person>();
		Person person = new Person();
		person.setEmail("email");
		person.setPhone("phone");
		person.setName("name");
		person.setId(1L);
		list.add(person);
		
		when(personDAO.findAll()).thenReturn(list);
		JSONArray actual = service.findAll();
		
		assertEquals(new Long(1), actual.getJSONObject(0).get("id"));
		assertEquals("email", actual.getJSONObject(0).get("email"));
		assertEquals("phone", actual.getJSONObject(0).get("phone"));
		assertEquals("name", actual.getJSONObject(0).get("name"));
	}
	
	@Test
	public void updatePersonShouldCallSavePerson() throws JSONException, IllegalArgumentException, IllegalAccessException {
		person.setId(id);
		
		when(personDAO.save(any(Person.class))).thenReturn(person);
		JSONObject actual = service.update(jsonPerson);
		
		assertEquals(actual.get("id"), id);
	}
	
	@Test
	public void deletePersonShouldCallDelete() throws JSONException {
		service.delete(jsonPerson);
		verify(personDAO).delete(any(Person.class));
	}
}
