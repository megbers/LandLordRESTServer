package com.landlordapp.webservice.domain;

import static com.landlordapp.webservice.domain.Person.EMAIL;
import static com.landlordapp.webservice.domain.Person.ID;
import static com.landlordapp.webservice.domain.Person.NAME;
import static com.landlordapp.webservice.domain.Person.PHONE;
import static com.landlordapp.webservice.domain.Person.PROPERTY;
import static com.landlordapp.webservice.domain.Person.TYPE;
import static com.landlordapp.webservice.domain.type.PersonType.TENANT;
import static java.lang.Long.parseLong;
import static junit.framework.Assert.assertEquals;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.landlordapp.webservice.domain.type.PersonType;

public class PersonTest {
	String id = "1001";
	Long longId = new Long(parseLong(id));
	String email = "email address";
	String phone = "phone";
	String name = "name";

	@Test
	public void contructorShouldPopulateFromJSONObjectWithID() throws JSONException {
		JSONObject object = new JSONObject();
		object.put(ID, id);
		object.put(EMAIL, email);
		object.put(PHONE, phone);
		object.put(NAME, name);
		object.put(TYPE, TENANT.toString());

		Person person = new Person(object);
		assertEquals(person.getId(), longId);
		assertEquals(person.getEmail(), email);
		assertEquals(person.getPhone(), phone);
		assertEquals(person.getName(), name);
		assertEquals(person.getType(), PersonType.TENANT);
	}

	@Test
	public void contructorShouldPopulateFromJSONObjectWithoutID() throws JSONException {
		JSONObject object = new JSONObject();
		object.put(EMAIL, email);
		object.put(PHONE, phone);
		object.put(NAME, name);
		object.put(TYPE, TENANT.toString());

		Person person = new Person(object);
		assertEquals(person.getEmail(), email);
		assertEquals(person.getPhone(), phone);
		assertEquals(person.getName(), name);
		assertEquals(person.getType(), PersonType.TENANT);
	}

	@Test
	public void contructorShouldPopulateFromJSONObjectWithProperty() throws JSONException {
		JSONObject object = new JSONObject();
		JSONObject property = new JSONObject();
		property.put(ID, "1");
		object.put(ID, id);
		object.put(EMAIL, email);
		object.put(PHONE, phone);
		object.put(NAME, name);
		object.put(TYPE, TENANT.toString());
		object.put(PROPERTY, property);

		Person person = new Person(object);
		assertEquals(person.getProperty().getId(), new Long(1));
	}

	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObject() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Person person = new Person();
		person.setId(parseLong(id));
		person.setEmail(email);
		person.setPhone(phone);
		person.setName(name);
		person.setType(TENANT);

		JSONObject object = person.toJSONObject();
		assertEquals(object.get(ID), longId);
		assertEquals(object.get(EMAIL), email);
		assertEquals(object.get(PHONE), phone);
		assertEquals(object.get(NAME), name);
		assertEquals(object.getString(TYPE), TENANT.toString());
	}

//	@Test
//	public void toJSONObjectShouldReturnAJSONRepresentationOfObjectWithProperty() throws JSONException, IllegalArgumentException, IllegalAccessException {
//		Person person = new Person();
//		Property property = new Property();
//		property.setId(1L);
//		person.setProperty(property);
//		person.setId(parseLong(id));
//		person.setEmail(email);
//		person.setPhone(phone);
//		person.setName(name);
//		person.setType(TENANT);
//
//		JSONObject object = person.toJSONObject();
//		assertEquals(object.getJSONObject(PROPERTY).getString(ID), "1");
//	}

}
