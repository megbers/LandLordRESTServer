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
	private JSONObject fakeProperty;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = "property id";
		fakeProperty = new JSONObject();
		fakeProperty.put("id", id);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray users = new JSONArray();
		when(service.findAll()).thenReturn(users);
		JSONArray actual = resource.findAllProperties();
		assertEquals(users, actual);
	}
	
	
	@Test
	public void findUserShouldReturnUserId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(fakeProperty);
		JSONObject user = resource.findProperty(id);
		assertEquals(user.get("id"), id);
	}
	
	@Test
	public void findUserShouldReturnNullIfNotFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(null);
		JSONObject user = resource.findProperty(id);
		assertNull(user);
	}
	
	@Test
	public void createUserShouldReturnUserId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.create(fakeProperty)).thenReturn(fakeProperty);
		JSONObject user = resource.createProperty(fakeProperty);
		assertEquals(user.get("id"), id);
	}
	
	@Test
	public void updateUserUserShouldReturnUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.update(fakeProperty)).thenReturn(fakeProperty);
		JSONObject user = resource.updateProperty(fakeProperty);
		assertEquals(user.get("id"), id);
	}
	
	@Test
	public void deleteUserShouldDeleteUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(fakeProperty);
		JSONObject json = resource.deleteProperty(id);
		verify(service).delete(fakeProperty);
		assertEquals(json.get("success"), 1);
	}

	
}
