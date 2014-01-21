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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.landlordapp.webservice.service.impl.MilesServiceI;
import com.landlordapp.webservice.service.impl.NoteServiceI;

public class MilesResourceTest {
	@Mock
	MilesServiceI service;
	@Mock
	NoteServiceI noteService;

	@InjectMocks
	private final MilesResource resource = new MilesResource();
	private String id;
	private JSONObject fakeMiles;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = "Miles id";
		fakeMiles = new JSONObject();
		fakeMiles.put("id", id);
		fakeMiles.put("milesDate", "");
		fakeMiles.put("property", "{id: 1}");
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
		when(noteService.create(Mockito.any(JSONObject.class))).thenReturn(new JSONObject());
		JSONObject miles = resource.createMiles(fakeMiles);
		verify(noteService).create(Mockito.any(JSONObject.class));
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
