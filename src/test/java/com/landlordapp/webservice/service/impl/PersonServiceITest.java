package com.landlordapp.webservice.service.impl;

import static com.landlordapp.webservice.domain.Person.USER_ID;
import static com.landlordapp.webservice.domain.Person.EMAIL;
import static com.landlordapp.webservice.domain.Person.ID;
import static com.landlordapp.webservice.domain.Person.NAME;
import static com.landlordapp.webservice.domain.Person.TYPE;
import static com.landlordapp.webservice.domain.type.PersonType.TENANT;
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
	private final String idString = "1001";
	private final Long id = 1001L;
	private Person person;
	private JSONObject jsonPerson;
	private final String userId = "This is the user id";

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
		jsonPerson.put(USER_ID, userId);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findOneShouldReturnPersonWhenOneIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		person.setId(id);
		when(personDAO.findById(id, userId)).thenReturn(person);

		JSONObject object = service.findOne(id, userId);
		assertEquals(object.get("id"), id);
	}

	@Test
	public void findOneShouldReturnNullWhenNoPersonIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(personDAO.findById(id, userId)).thenReturn(null);

		JSONObject object = service.findOne(id, userId);
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

		when(personDAO.findAll(userId)).thenReturn(list);
		JSONArray actual = service.findAll(userId);

		assertEquals(new Long(1), actual.getJSONObject(0).get("id"));
		assertEquals("email", actual.getJSONObject(0).get("email"));
		assertEquals("phone", actual.getJSONObject(0).get("phone"));
		assertEquals("name", actual.getJSONObject(0).get("name"));
	}

	@Test
	public void findByPropertyShouldCallFindAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Long propertyId = 1L;
		List<Person> list = new ArrayList<Person>();

		Person person = new Person();
		person.setId(1L);
		person.setEmail("email");
		person.setName("Test");
		person.setType(TENANT);
		list.add(person);

		when(personDAO.findByProperty(propertyId, userId)).thenReturn(list);
		JSONArray actual = service.findByProperty(propertyId, userId);

		assertEquals(new Long(1), actual.getJSONObject(0).get(ID));
		assertEquals(TENANT.toString(), actual.getJSONObject(0).getString(TYPE));
		assertEquals("Test", actual.getJSONObject(0).getString(NAME));
		assertEquals("email", actual.getJSONObject(0).getString(EMAIL));
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
