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

import com.landlordapp.webservice.data.PropertyDAO;
import com.landlordapp.webservice.domain.Property;

public class PropertyServiceITest {
	@Mock
	private PropertyDAO propertyDAO;
	@InjectMocks
	private PropertyServiceI service;
	private String idString = "1001";
	private String mortgageString = "1234.56";
	private Long id = 1001L;
	private Property property;
	private JSONObject jsonProperty;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		service = new PropertyServiceI();
		property = new Property();
		jsonProperty = new JSONObject();
		jsonProperty.put("id", idString);
		jsonProperty.put("address", "address");
		jsonProperty.put("mortgage", mortgageString);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findOneShouldReturnUserWhenOneIsFound() throws JSONException {
		property.setId(id);
		when(propertyDAO.findById(id)).thenReturn(property);
		
		JSONObject object = service.findOne(idString);
		assertEquals(object.get("id"), id);
	}
	
	@Test
	public void findOneShouldReturnNullWhenNoUserIsFound() throws JSONException {
		when(propertyDAO.findById(id)).thenReturn(null);
		
		JSONObject object = service.findOne(idString);
		assertNull(object);
	}
	
	@Test
	public void createUserShouldCallSaveUser() throws JSONException {
		property.setId(id);
		
		when(propertyDAO.save(any(Property.class))).thenReturn(property);
		JSONObject actual = service.create(jsonProperty);
		
		assertEquals(actual.get("id"), id);
	}
	
	@Test
	public void findAllShouldCallFindAll() throws JSONException {
		List<Property> list = new ArrayList<Property>();
		Property property = new Property();
		property.setAddress("address");
		property.setMortgage(1234.56D);
		property.setId(1L);
		list.add(property);
		
		when(propertyDAO.findAll()).thenReturn(list);
		JSONArray actual = service.findAll();
		
		assertEquals(new Long(1), actual.getJSONObject(0).get("id"));
		assertEquals("address", actual.getJSONObject(0).get("address"));
		assertEquals(new Double(1234.56), actual.getJSONObject(0).get("mortgage"));
	}
	
	@Test
	public void updateUserShouldCallSaveUser() throws JSONException {
		property.setId(id);
		
		when(propertyDAO.save(any(Property.class))).thenReturn(property);
		JSONObject actual = service.update(jsonProperty);
		
		assertEquals(actual.get("id"), id);
	}
	
	@Test
	public void deleteUserShouldCallDelete() throws JSONException {
		service.delete(jsonProperty);
		verify(propertyDAO).delete(any(Property.class));
	}
}
