package com.landlordapp.webservice.domain;

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
		object.put("id", id);
		object.put("email", email);
		object.put("phone", phone);
		object.put("name", name);
		object.put("type", PersonType.TENANT.toString());
		
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
		object.put("email", email);
		object.put("phone", phone);
		object.put("name", name);
		object.put("type", PersonType.TENANT.toString());
		
		Person person = new Person(object);
		assertEquals(person.getEmail(), email);
		assertEquals(person.getPhone(), phone);
		assertEquals(person.getName(), name);
		assertEquals(person.getType(), PersonType.TENANT);
	}
	
	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObject() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Person person = new Person();
		person.setId(parseLong(id));
		person.setEmail(email);
		person.setPhone(phone);
		person.setName(name);
		person.setType(PersonType.TENANT);
		
		JSONObject object = person.toJSONObject();
		assertEquals(object.get("id"), longId);
		assertEquals(object.get("email"), email);
		assertEquals(object.get("phone"), phone);
		assertEquals(object.get("name"), name);
		assertEquals(object.getString("type"), PersonType.TENANT.toString());
	}
}
