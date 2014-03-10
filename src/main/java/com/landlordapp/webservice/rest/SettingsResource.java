package com.landlordapp.webservice.rest;

import static com.landlordapp.webservice.domain.Property.USER_ID;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landlordapp.webservice.service.SettingsService;

@Component
@Path("settings")
public class SettingsResource {
	@Autowired
	private SettingsService settingsService;
	
	@GET
	@Path("find/{id}")
	@Produces(APPLICATION_JSON)
	public JSONObject findSettings(@PathParam("id") Long id,
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject response = settingsService.findOne(id, userId);
		return response;
	}
	
	@PUT
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject createSettings(JSONObject settings,
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		settings.put(USER_ID, userId);
		JSONObject newSettings = settingsService.create(settings);
		return newSettings;
	}
	
	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject updateSettings(JSONObject settings,
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		settings.put(USER_ID, userId);
		JSONObject newSettings = settingsService.update(settings);
		return newSettings;
	}
	
	
	public void setSettingsService(SettingsService service) {
		this.settingsService = service;
	}
}
