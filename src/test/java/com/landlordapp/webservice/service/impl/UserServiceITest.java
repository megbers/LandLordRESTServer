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

import com.landlordapp.webservice.data.UserDAO;
import com.landlordapp.webservice.domain.User;

public class UserServiceITest {
	@Mock
	private UserDAO userDAO;
	@InjectMocks
	private UserServiceI service;
	private String idString = "1001";
	private Long id = 1001L;
	private User user;
	private JSONObject jsonUser;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		service = new UserServiceI();
		user = new User();
		jsonUser = new JSONObject();
		jsonUser.put("id", idString);
		jsonUser.put("email", "email");
		jsonUser.put("password", "password");
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findOneShouldReturnUserWhenOneIsFound() throws JSONException {
		user.setId(id);
		when(userDAO.findById(id)).thenReturn(user);
		
		JSONObject object = service.findOne(idString);
		assertEquals(object.get("id"), id);
	}
	
	@Test
	public void findOneShouldReturnNullWhenNoUserIsFound() throws JSONException {
		when(userDAO.findById(id)).thenReturn(null);
		
		JSONObject object = service.findOne(idString);
		assertNull(object);
	}
	
	@Test
	public void createUserShouldCallSaveUser() throws JSONException {
		user.setId(id);
		
		when(userDAO.save(any(User.class))).thenReturn(user);
		JSONObject actual = service.create(jsonUser);
		
		assertEquals(actual.get("id"), id);
	}
	
	@Test
	public void findAllShouldCallFindAll() throws JSONException {
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
		user.setId(1L);
		list.add(user);
		
		when(userDAO.findAll()).thenReturn(list);
		JSONArray actual = service.findAll();
		
		assertEquals(new Long(1), actual.getJSONObject(0).get("id"));
		assertEquals("email", actual.getJSONObject(0).get("email"));
		assertEquals("password", actual.getJSONObject(0).get("password"));
	}
	
	@Test
	public void updateUserShouldCallSaveUser() throws JSONException {
		user.setId(id);
		
		when(userDAO.save(any(User.class))).thenReturn(user);
		JSONObject actual = service.update(jsonUser);
		
		assertEquals(actual.get("id"), id);
	}
	
	@Test
	public void deleteUserShouldCallDelete() throws JSONException {
		service.delete(jsonUser);
		verify(userDAO).delete(any(User.class));
	}
}
