package com.landlordapp.webservice.domain;

import static java.lang.Long.parseLong;
import static junit.framework.Assert.assertEquals;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

public class PersonTest {
	String id = "1001";
	Long longId = new Long(parseLong(id));
	String email = "email address";
	String phone = "phone";
	String name = "name";
	
	@Test
	public void contructorShouldPopulateFromJSONObjectWithID() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("email", email);
		object.put("phone", phone);
		object.put("name", name);
		
		Person person = new Person(object);
		assertEquals(person.getId(), longId);
		assertEquals(person.getEmail(), email);
		assertEquals(person.getPhone(), phone);
		assertEquals(person.getName(), name);
	}
	
	@Test
	public void contructorShouldPopulateFromJSONObjectWithoutID() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("email", email);
		object.put("phone", phone);
		object.put("name", name);
		
		Person person = new Person(object);
		assertEquals(person.getEmail(), email);
		assertEquals(person.getPhone(), phone);
		assertEquals(person.getName(), name);
	}
	
	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObject() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Person person = new Person();
		person.setId(parseLong(id));
		person.setEmail(email);
		person.setPhone(phone);
		person.setName(name);
		
		JSONObject object = person.toJSONObject();
		assertEquals(object.get("id"), longId);
		assertEquals(object.get("email"), email);
		assertEquals(object.get("phone"), phone);
		assertEquals(object.get("name"), name);
	}
}
