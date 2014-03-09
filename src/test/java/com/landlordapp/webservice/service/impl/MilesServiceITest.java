package com.landlordapp.webservice.service.impl;

import static com.landlordapp.webservice.domain.Miles.ID;
import static com.landlordapp.webservice.domain.Miles.NUMBER_OF_MILES;
import static com.landlordapp.webservice.domain.Miles.USER_ID;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.landlordapp.webservice.data.MilesDAO;
import com.landlordapp.webservice.domain.Miles;
import com.landlordapp.webservice.domain.Property;

public class MilesServiceITest {
	@Mock
	private MilesDAO milesDAO;
	@InjectMocks
	private MilesServiceI service;
	private final String idString = "1001";
	private final Long id = 1001L;
	private Miles miles;
	private JSONObject jsonMiles;
	private final String userId = "This is the user id";
	public Date date;
	public Double numberOfMiles;
	public Property property;
	public String milesType;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		property = new Property();
		service = new MilesServiceI();
		miles = new Miles();
		numberOfMiles = 123.45D;
		jsonMiles = new JSONObject();
		jsonMiles.put("id", idString);
		jsonMiles.put("numberOfMiles", numberOfMiles);
		jsonMiles.put("property", property);
		jsonMiles.put("milesType", "BANK");
		jsonMiles.put(USER_ID, userId);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findOneShouldReturnUserWhenOneIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		miles.setId(id);
		when(milesDAO.findById(id, userId)).thenReturn(miles);

		JSONObject object = service.findOne(idString, userId);
		assertEquals(object.get("id"), id);
	}

	@Test
	public void findOneShouldReturnNullWhenNoUserIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(milesDAO.findById(id, userId)).thenReturn(null);

		JSONObject object = service.findOne(idString, userId);
		assertNull(object);
	}

	@Test
	public void createUserShouldCallSaveUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		miles.setId(id);

		when(milesDAO.save(any(Miles.class))).thenReturn(miles);
		JSONObject actual = service.create(jsonMiles);

		assertEquals(actual.get("id"), id);
	}

	@Test
	public void findAllShouldCallFindAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Miles> list = new ArrayList<Miles>();
		Miles miles = new Miles();
		miles.setNumberOfMiles(123.45D);
		miles.setProperty(property);
		miles.setId(1L);
		list.add(miles);

		when(milesDAO.findAll(userId)).thenReturn(list);
		JSONArray actual = service.findAll(userId);

		assertEquals(new Long(1), actual.getJSONObject(0).get("id"));
		assertEquals("123.45", actual.getJSONObject(0).getString("numberOfMiles"));
	}

	@Test
	public void findByPropertyShouldCallFindAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Long propertyId = 1L;
		List<Miles> list = new ArrayList<Miles>();
		Miles miles = new Miles();
		miles.setNumberOfMiles(1234.56D);
		miles.setId(1L);
		list.add(miles);

		when(milesDAO.findByProperty(propertyId, userId)).thenReturn(list);
		JSONArray actual = service.findByProperty(propertyId, userId);

		assertEquals(new Long(1), actual.getJSONObject(0).get(ID));
		assertEquals("1234.56", actual.getJSONObject(0).getString(NUMBER_OF_MILES));
	}

	@Test
	public void updateUserShouldCallSaveUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		miles.setId(id);

		when(milesDAO.save(any(Miles.class))).thenReturn(miles);
		JSONObject actual = service.update(jsonMiles);

		assertEquals(actual.get("id"), id);
	}

	@Test
	public void deleteUserShouldCallDelete() throws JSONException {
		service.delete(jsonMiles);
		verify(milesDAO).delete(any(Miles.class));
	}
}
