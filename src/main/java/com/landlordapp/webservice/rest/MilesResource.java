package com.landlordapp.webservice.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landlordapp.webservice.service.MilesService;

@Component
@Path("miles")
public class MilesResource {
	@Autowired
	private MilesService milesService;
	
	@GET
	@Path("findAll")
	@Produces(APPLICATION_JSON)
	public JSONArray findAllMiless() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = milesService.findAll();
		return response;
	}
	
	@GET
	@Path("findByProperty/{propertyId}")
	@Produces(APPLICATION_JSON)
	public JSONArray findMilessByProperty(@PathParam("propertyId") Long propertyId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = milesService.findByProperty(propertyId);
		return response;
	}
	
	@GET
	@Path("find/{id}")
	@Produces(APPLICATION_JSON)
	public JSONObject findMiles(@PathParam("id") String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject response = milesService.findOne(id);
		return response;
	}
	
	@PUT
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject createMiles(JSONObject miles) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject newMiles = milesService.create(miles);
		return newMiles;
	}
	
	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject updateMiles(JSONObject miles) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject newMiles = milesService.update(miles);
		return newMiles;
	}
	
	@DELETE
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_FORM_URLENCODED)
	public JSONObject deleteMiles(@FormParam("id") String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject miles = milesService.findOne(id);
		milesService.delete(miles);
		JSONObject object = new JSONObject();
		object.put("success", 1);
		return object;
	}
	
	public void setMilesService(MilesService service) {
		this.milesService = service;
	}
}

