package com.landlordapp.webservice.rest;

import static com.landlordapp.webservice.domain.Miles.USER_ID;
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

import com.landlordapp.webservice.service.MilesService;
import com.landlordapp.webservice.service.NoteService;

@Component
@Path("miles")
public class MilesResource {
	@Autowired
	private MilesService milesService;
	@Autowired
	private NoteService noteService;
	
	@GET
	@Path("findAll")
	@Produces(APPLICATION_JSON)
	public JSONArray findAllMiless(@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = milesService.findAll(userId);
		return response;
	}
	
	@GET
	@Path("findByProperty/{propertyId}")
	@Produces(APPLICATION_JSON)
	public JSONArray findMilessByProperty(@PathParam("propertyId") Long propertyId,
			@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = milesService.findByProperty(propertyId, userId);
		return response;
	}
	
	@GET
	@Path("find/{id}")
	@Produces(APPLICATION_JSON)
	public JSONObject findMiles(@PathParam("id") String id,
			@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject response = milesService.findOne(id, userId);
		return response;
	}
	
	@PUT
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject createMiles(JSONObject miles,
			@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		miles.put(USER_ID, userId);
		JSONObject newMiles = milesService.create(miles);
		JSONObject newNote = new JSONObject();
		newNote.put("text", "New Miles Created");
		newNote.put("date", miles.get("milesDate"));
		newNote.put("property", miles.get("property"));
		noteService.create(newNote);
		return newMiles;
	}
	
	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject updateMiles(JSONObject miles,
			@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		miles.put(USER_ID, userId);
		JSONObject newMiles = milesService.update(miles);
		return newMiles;
	}
	
	@DELETE
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_FORM_URLENCODED)
	public JSONObject deleteMiles(@FormParam("id") String id,
			@HeaderParam("uesrId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject miles = milesService.findOne(id, userId);
		milesService.delete(miles);
		JSONObject object = new JSONObject();
		object.put("success", 1);
		return object;
	}
	
	public void setMilesService(MilesService service) {
		this.milesService = service;
	}

	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}
}

