package com.landlordapp.webservice.rest;

import static com.landlordapp.webservice.domain.Note.USER_ID;
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

import com.landlordapp.webservice.service.NoteService;

@Component
@Path("note")
public class NoteResource {
	@Autowired
	private NoteService noteService;
	
	@GET
	@Path("findAll")
	@Produces(APPLICATION_JSON)
	public JSONArray findAllNotes(@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = noteService.findAll(userId);
		return response;
	}
	
	@GET
	@Path("findByProperty/{propertyId}")
	@Produces(APPLICATION_JSON)
	public JSONArray findNotesByProperty(@PathParam("propertyId") Long propertyId,
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = noteService.findByProperty(propertyId, userId);
		return response;
	}
	
	@GET
	@Path("find/{id}")
	@Produces(APPLICATION_JSON)
	public JSONObject findNote(@PathParam("id") Long id,
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject response = noteService.findOne(id, userId);
		return response;
	}
	
	@PUT
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject createNote(JSONObject note,
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		note.put(USER_ID, userId);
		JSONObject newNote = noteService.create(note);
		return newNote;
	}
	
	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject updateNote(JSONObject note,
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		note.put(USER_ID, userId);
		JSONObject newNote = noteService.update(note);
		return newNote;
	}
	
	@DELETE
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_FORM_URLENCODED)
	public JSONObject deleteNote(@FormParam("id") Long id,
			@HeaderParam("userId") String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject note = noteService.findOne(id, userId);
		noteService.delete(note);
		JSONObject object = new JSONObject();
		object.put("success", 1);
		return object;
	}
	
	public void setNoteService(NoteService service) {
		this.noteService = service;
	}
}

