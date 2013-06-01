package com.landlordapp.webservice.domain;

import static java.lang.Long.parseLong;
import static junit.framework.Assert.assertEquals;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

public class UserTest {
	String id = "1001";
	Long longId = new Long(parseLong(id));
	String email = "email address";
	String password = "password";
	
	@Test
	public void contructorShouldPopulateFromJSONObjectWithID() throws JSONException {
		
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("email", email);
		object.put("password", password);
		
		User user = new User(object);
		assertEquals(user.getId(), longId);
		assertEquals(user.getEmail(), email);
		assertEquals(user.getPassword(), password);
	}
	
	@Test
	public void contructorShouldPopulateFromJSONObjectWithoutID() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("email", email);
		object.put("password", password);
		
		User user = new User(object);
		assertEquals(user.getEmail(), email);
		assertEquals(user.getPassword(), password);
	}
	
	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObject() throws JSONException {
		User user = new User();
		user.setId(parseLong(id));
		user.setEmail(email);
		user.setPassword(password);
		
		JSONObject object = user.toJSONObject();
		assertEquals(object.get("id"), longId);
		assertEquals(object.get("email"), email);
		assertEquals(object.get("password"), password);
		
	}
	
}
