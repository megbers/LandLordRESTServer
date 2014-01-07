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

import com.landlordapp.webservice.service.UserService;

@Component
@Path("users")
public class UserResource {
	@Autowired
	private UserService userService;
	
	@GET
	@Path("findAll")
	@Produces(APPLICATION_JSON)
	public JSONArray findAllUsers() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = userService.findAll();
		return response;
	}
	
	@GET
	@Path("find/{id}")
	@Produces(APPLICATION_JSON)
	public JSONObject findUser(@PathParam("id") String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject response = userService.findOne(id);
		return response;
	}
	
	@PUT
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject createUser(JSONObject user) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject newUser = userService.create(user);
		return newUser;
	}
	
	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject updateUser(JSONObject user) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject newUser = userService.update(user);
		return newUser;
	}
	
	@POST
	@Path("login")
	@Produces(APPLICATION_JSON)
	public JSONObject loginUser(JSONObject user) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject response = userService.login(user);
		return response;
	}
	
	@DELETE
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_FORM_URLENCODED)
	public JSONObject deleteUser(@FormParam("id") String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject user = userService.findOne(id);
		userService.delete(user);
		JSONObject object = new JSONObject();
		object.put("success", 1);
		return object;
	}
	
	public void setUserService(UserService service) {
		this.userService = service;
	}
}
