package com.landlordapp.webservice.service.impl;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.data.SettingsDAO;
import com.landlordapp.webservice.domain.Settings;
import com.landlordapp.webservice.service.SettingsService;

public class SettingsServiceI implements SettingsService{
	
	public SettingsDAO settingsDAO;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public JSONObject findOne(final Long id, final String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Settings settings = settingsDAO.findById(id, userId);
		if (settings == null) {
			return null;
		}
		return settings.toJSONObject();
	}

	public JSONObject create(final JSONObject jsonExpense) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Settings settings = new Settings(jsonExpense);
		return settingsDAO.save(settings).toJSONObject();
	}

	public JSONObject update(final JSONObject jsonExpense) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Settings settings = new Settings(jsonExpense);
		return settingsDAO.save(settings).toJSONObject();
	}

}
