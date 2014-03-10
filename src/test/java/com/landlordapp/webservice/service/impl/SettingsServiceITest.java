package com.landlordapp.webservice.service.impl;

import static com.landlordapp.webservice.domain.type.RentType.MONTHY;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.landlordapp.webservice.data.SettingsDAO;
import com.landlordapp.webservice.domain.Settings;
import com.landlordapp.webservice.domain.type.RentType;

public class SettingsServiceITest {
	@Mock
	private SettingsDAO settingsDAO;
	@InjectMocks
	private SettingsServiceI service;
	private final String idString = "1001";
	private final String userId = "This is the user id";
	private final Long id = 1001L;
	private Settings settings;
	private JSONObject jsonSettings;
	private String vacancy = "vacancyRate";
	private String advertiserURL = "advertiserURL";
	private RentType rentType = MONTHY;
	
	@Before
	public void doBeforeEachTestCase() throws JSONException {
		service = new SettingsServiceI();
		settings = new Settings();
		jsonSettings = new JSONObject();
		jsonSettings.put(Settings.ID, idString);
		jsonSettings.put(Settings.VACANCY, vacancy);
		jsonSettings.put(Settings.ADVERTISER_URL, advertiserURL);
		jsonSettings.put(Settings.RENT_TYPE, rentType);
		jsonSettings.put(Settings.USER_ID, userId);
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findOneShouldReturnUserWhenOneIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		settings.setId(id);
		when(settingsDAO.findById(id, userId)).thenReturn(settings);

		JSONObject object = service.findOne(id, userId);
		assertEquals(object.get(Settings.ID), id);
	}

	@Test
	public void findOneShouldReturnNullWhenNoUserIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(settingsDAO.findById(id, userId)).thenReturn(null);

		JSONObject object = service.findOne(id, userId);
		assertNull(object);
	}

	@Test
	public void createUserShouldCallSaveUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		settings.setId(id);

		when(settingsDAO.save(any(Settings.class))).thenReturn(settings);
		JSONObject actual = service.create(jsonSettings);

		assertEquals(actual.get(Settings.ID), id);
	}
}
