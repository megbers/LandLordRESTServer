package com.landlordapp.webservice.rest;

import static com.landlordapp.webservice.domain.Settings.USER_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.landlordapp.webservice.service.impl.SettingsServiceI;

public class SettingsResourceTest {
	@Mock
	SettingsServiceI service;
	@InjectMocks
	private final SettingsResource resource = new SettingsResource();
	private Long id;
	private String userId = "this is the user id";
	private JSONObject fakeSettings;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = 1L;
		fakeSettings = new JSONObject();
		fakeSettings.put("id", id);
		fakeSettings.put("userId", userId);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findSettingsShouldReturnSettingsId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(fakeSettings);
		JSONObject settings = resource.findSettings(id, userId);
		assertEquals(settings.get("id"), id);
	}

	@Test
	public void findSettingsShouldReturnNullIfNotFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(null);
		JSONObject settings = resource.findSettings(id, userId);
		assertNull(settings);
	}

	@Test
	public void createSettingsShouldReturnSettingsId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.create(fakeSettings)).thenReturn(fakeSettings);
		JSONObject settings = resource.createSettings(fakeSettings, userId);
		assertEquals(settings.get("id"), id);
		assertEquals(settings.get(USER_ID), userId);
	}

	@Test
	public void updateSettingsSettingsShouldReturnSettings() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.update(fakeSettings)).thenReturn(fakeSettings);
		JSONObject settings = resource.updateSettings(fakeSettings, userId);
		assertEquals(settings.get("id"), id);
		assertEquals(settings.get(USER_ID), userId);
	}

}
