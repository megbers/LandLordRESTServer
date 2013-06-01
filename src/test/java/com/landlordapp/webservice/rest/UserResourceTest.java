package com.landlordapp.webservice.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.landlordapp.webservice.service.impl.UserServiceI;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest {
	@Mock
	UserServiceI service;
	@InjectMocks
	private UserResource resource = new UserResource();
	private String id;
	private JSONObject fakeUser;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = "user id";
		fakeUser = new JSONObject();
		fakeUser.put("id", id);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findUserShouldReturnUserId() throws JSONException {
		when(service.findOne(id)).thenReturn(fakeUser);
		JSONObject user = resource.findUser(id);
		assertEquals(user.get("id"), id);
	}
	
	@Test
	public void findUserShouldReturnNullIfNotFound() throws JSONException {
		when(service.findOne(id)).thenReturn(null);
		JSONObject user = resource.findUser(id);
		assertNull(user);
	}
	
	@Test
	public void createUserShouldReturnUserId() throws JSONException {
		when(service.createUser(fakeUser)).thenReturn(fakeUser);
		JSONObject user = resource.createUser(fakeUser);
		assertEquals(user.get("id"), id);
	}
	
	@Test
	public void updateUserUserShouldReturnUser() throws JSONException {
		when(service.updateUser(fakeUser)).thenReturn(fakeUser);
		JSONObject user = resource.updateUser(fakeUser);
		assertEquals(user.get("id"), id);
	}
	
	@Test
	public void deleteUserShouldDeleteUser() throws JSONException {
		JSONObject json = resource.deleteUser(fakeUser);
		verify(service).deleteUser(fakeUser);
		assertEquals(json.get("success"), 1);
	}

	
}
