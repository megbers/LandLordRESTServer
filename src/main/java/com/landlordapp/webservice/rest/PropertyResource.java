package com.landlordapp.webservice.rest;

import static com.landlordapp.webservice.domain.Property.USER_ID;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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

import com.landlordapp.webservice.service.PropertyService;

@Component
@Path("properties")
public class PropertyResource {
	@Autowired
	private PropertyService propertyService;
	
	@GET
	@Path("findAll")
	@Produces(APPLICATION_JSON)
	public JSONArray findAllProperties(@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = propertyService.findAll(userId);
		return response;
	}
	
	@GET
	@Path("find/{id}")
	@Produces(APPLICATION_JSON)
	public JSONObject findProperty(@PathParam("id") Long id,
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject response = propertyService.findOne(id, userId);
		return response;
	}
	 
	@PUT
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject createProperty(JSONObject property, 
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		property.put(USER_ID, userId);
		JSONObject newProperty = propertyService.create(property);
		return newProperty;
	}
	
	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject updateProperty(JSONObject property,
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		property.put(USER_ID, userId);
		JSONObject newProperty = propertyService.update(property);
		return newProperty;
	}
	
	@DELETE
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_FORM_URLENCODED)
	public JSONObject deleteProperty(@FormParam("id") Long id,
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject property = propertyService.findOne(id, userId);
		propertyService.delete(property);
		JSONObject object = new JSONObject();
		object.put("success", 1);
		return object;
	}
	
	public void setPropertyService(PropertyService service) {
		this.propertyService = service;
	}
}
