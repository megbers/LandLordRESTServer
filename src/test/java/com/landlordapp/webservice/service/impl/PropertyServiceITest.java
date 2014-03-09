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

import com.landlordapp.webservice.data.PersonDAO;
import com.landlordapp.webservice.data.PropertyDAO;
import com.landlordapp.webservice.domain.Person;
import com.landlordapp.webservice.domain.Property;

public class PropertyServiceITest {
	@Mock
	private PropertyDAO propertyDAO;
	@Mock
	private PersonDAO personDAO;
	@InjectMocks
	private PropertyServiceI service;
	private String idString = "1001";
	private String mortgageString = "1234.56";
	private String userId = "userId";
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
		jsonProperty.put("userId", userId);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findOneShouldReturnUserWhenOneIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		property.setId(id);
		when(propertyDAO.findById(id, userId)).thenReturn(property);
		
		JSONObject object = service.findOne(idString, userId);
		assertEquals(object.get("id"), id);
	}
	
	@Test
	public void findOneShouldReturnNullWhenNoUserIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(propertyDAO.findById(id, userId)).thenReturn(null);
		
		JSONObject object = service.findOne(idString, userId);
		assertNull(object);
	}
	
	@Test
	public void createUserShouldCallSaveUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		property.setId(id);
		
		when(propertyDAO.save(any(Property.class))).thenReturn(property);
		JSONObject actual = service.create(jsonProperty);
		
		assertEquals(actual.get("id"), id);
	}
	
	@Test
	public void createPropertyShouldIncludeTenantIfItExists() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Long personId = new Long(100);
		jsonProperty.put("tenant", personId);
		
		Person person = new Person();
		person.setId(personId);
		property.getTenants().add(person);
		
		when(personDAO.findById(personId, userId)).thenReturn(person);
		when(propertyDAO.save(any(Property.class))).thenReturn(property);
		JSONObject actual = service.create(jsonProperty);
		
		assertEquals(new Long(actual.getJSONArray("tenants").getJSONObject(0).getLong("id")), personId);
	}
	
	@Test
	public void findAllShouldCallFindAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Property> list = new ArrayList<Property>();
		Property property = new Property();
		property.setAddress("address");
		property.setMortgage(1234.56D);
		property.setId(1L);
		list.add(property);
		
		when(propertyDAO.findAll(userId)).thenReturn(list);
		JSONArray actual = service.findAll(userId);
		
		assertEquals(new Long(1), actual.getJSONObject(0).get("id"));
		assertEquals("address", actual.getJSONObject(0).get("address"));
		assertEquals(new Double(1234.56), actual.getJSONObject(0).get("mortgage"));
	}
	
	@Test
	public void updateUserShouldCallSaveUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
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
