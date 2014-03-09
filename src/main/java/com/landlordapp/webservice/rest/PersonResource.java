package com.landlordapp.webservice.rest;

import static com.landlordapp.webservice.domain.Person.USER_ID;
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

import com.landlordapp.webservice.service.PersonService;

@Component
@Path("person")
public class PersonResource {
	@Autowired
	private PersonService personService;

	@GET
	@Path("findAll")
	@Produces(APPLICATION_JSON)
	public JSONArray findAllPersons(@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = personService.findAll(userId);
		return response;
	}

	@GET
	@Path("find/{id}")
	@Produces(APPLICATION_JSON)
	public JSONObject findPerson(@PathParam("id") final String id,
			@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject response = personService.findOne(id, userId);
		return response;
	}

	@GET
	@Path("findByProperty/{propertyId}")
	@Produces(APPLICATION_JSON)
	public JSONArray findPersonByProperty(@PathParam("propertyId") Long propertyId,
			@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = personService.findByProperty(propertyId, userId);
		return response;
	}

	@PUT
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject createPerson(final JSONObject person,
			@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		person.put(USER_ID, userId);
		JSONObject newPerson = personService.create(person);
		return newPerson;
	}

	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject updatePerson(final JSONObject person,
			@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		person.put(USER_ID, userId);
		JSONObject newPerson = personService.update(person);
		return newPerson;
	}

	@DELETE
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_FORM_URLENCODED)
	public JSONObject deletePerson(@FormParam("id") final String id,
			@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject person = personService.findOne(id, userId);
		personService.delete(person);
		JSONObject object = new JSONObject();
		object.put("success", 1);
		return object;
	}

	public void setPersonService(final PersonService service) {
		this.personService = service;
	}
}
