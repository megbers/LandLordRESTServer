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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.landlordapp.webservice.service.impl.MilesServiceI;

public class MilesResourceTest {
	@Mock
	MilesServiceI service;
	@InjectMocks
	private MilesResource resource = new MilesResource();
	private String id;
	private JSONObject fakeMiles;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = "Miles id";
		fakeMiles = new JSONObject();
		fakeMiles.put("id", id);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray milesList = new JSONArray();
		when(service.findAll()).thenReturn(milesList);
		JSONArray actual = resource.findAllMiless();
		assertEquals(milesList, actual);
	}
	
	@Test
	public void findByPropertyShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Long propertyId = 1L;
		JSONArray milesList = new JSONArray();
		when(service.findByProperty(propertyId)).thenReturn(milesList);
		JSONArray actual = resource.findMilessByProperty(propertyId);
		assertEquals(milesList, actual);
	}
	
	
	@Test
	public void findMilesShouldReturnMilesId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(fakeMiles);
		JSONObject miles = resource.findMiles(id);
		assertEquals(miles.get("id"), id);
	}
	
	@Test
	public void findMilesShouldReturnNullIfNotFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(null);
		JSONObject miles = resource.findMiles(id);
		assertNull(miles);
	}
	
	@Test
	public void createMilesShouldReturnMilesId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.create(fakeMiles)).thenReturn(fakeMiles);
		JSONObject miles = resource.createMiles(fakeMiles);
		assertEquals(miles.get("id"), id);
	}
	
	@Test
	public void updateMilesMilesShouldReturnMiles() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.update(fakeMiles)).thenReturn(fakeMiles);
		JSONObject miles = resource.updateMiles(fakeMiles);
		assertEquals(miles.get("id"), id);
	}
	
	@Test
	public void deleteMilesShouldDeleteMiles() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(fakeMiles);
		JSONObject json = resource.deleteMiles(id);
		verify(service).delete(fakeMiles);
		assertEquals(json.get("success"), 1);
	}
}
