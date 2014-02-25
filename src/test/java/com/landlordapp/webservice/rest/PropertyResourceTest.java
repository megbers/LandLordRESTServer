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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.landlordapp.webservice.service.impl.PropertyServiceI;

@RunWith(MockitoJUnitRunner.class)
public class PropertyResourceTest {
	@Mock
	PropertyServiceI service;
	@InjectMocks
	private PropertyResource resource = new PropertyResource();
	private String id;
	private String userId;
	private JSONObject fakeProperty;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = "property id";
		userId = "userId";
		fakeProperty = new JSONObject();
		fakeProperty.put("id", id);
		fakeProperty.put("userId", userId);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray users = new JSONArray();
		when(service.findAll(userId)).thenReturn(users);
		JSONArray actual = resource.findAllProperties(userId);
		assertEquals(users, actual);
	}
	
	
	@Test
	public void findOneShouldReturnUserId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(fakeProperty);
		JSONObject property = resource.findProperty(userId, id);
		assertEquals(property.get("id"), id);
	}
	
	@Test
	public void findOneShouldReturnNullIfNotFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(null);
		JSONObject property = resource.findProperty(userId, id);
		assertNull(property);
	}
	
	@Test
	public void createUserShouldReturnUserId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.create(fakeProperty)).thenReturn(fakeProperty);
		JSONObject user = resource.createProperty(userId, fakeProperty);
		assertEquals(user.get("id"), id);
	}
	
	@Test
	public void updateUserUserShouldReturnUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.update(fakeProperty)).thenReturn(fakeProperty);
		JSONObject user = resource.updateProperty(userId, fakeProperty);
		assertEquals(user.get("id"), id);
	}
	
	@Test
	public void deleteUserShouldDeleteUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(fakeProperty);
		JSONObject json = resource.deleteProperty(userId, id);
		verify(service).delete(fakeProperty);
		assertEquals(json.get("success"), 1);
	}

	
}
