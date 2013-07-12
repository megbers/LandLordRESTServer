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

import com.landlordapp.webservice.service.PersonService;

@Component
@Path("person")
public class PersonResource {
	@Autowired
	private PersonService personService;
	
	@GET
	@Path("findAll")
	@Produces(APPLICATION_JSON)
	public JSONArray findAllPersons() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = personService.findAll();
		return response;
	}
	
	@GET
	@Path("find/{id}")
	@Produces(APPLICATION_JSON)
	public JSONObject findPerson(@PathParam("id") String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject response = personService.findOne(id);
		return response;
	}
	
	@PUT
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject createPerson(JSONObject person) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject newPerson = personService.create(person);
		return newPerson;
	}
	
	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject updatePerson(JSONObject person) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject newPerson = personService.update(person);
		return newPerson;
	}
	
	@DELETE
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_FORM_URLENCODED)
	public JSONObject deletePerson(@FormParam("id") String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject person = personService.findOne(id);
		personService.delete(person);
		JSONObject object = new JSONObject();
		object.put("success", 1);
		return object;
	}
	
	public void setPersonService(PersonService service) {
		this.personService = service;
	}
}
