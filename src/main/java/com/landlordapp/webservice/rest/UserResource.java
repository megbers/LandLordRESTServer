package com.landlordapp.webservice.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
	@Path("find/{id}")
	@Produces(APPLICATION_JSON)
	public JSONObject findUser(@PathParam("id") String id) throws JSONException {
		JSONObject response = userService.findOne(id);
		return response;
	}
	
	@PUT
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject createUser(JSONObject user) throws JSONException {
		JSONObject newUser = userService.createUser(user);
		return newUser;
	}
	
	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject updateUser(JSONObject user) throws JSONException {
		JSONObject newUser = userService.updateUser(user);
		return newUser;
	}
	
	@DELETE
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject deleteUser(JSONObject user) throws JSONException {
		userService.deleteUser(user);
		JSONObject object = new JSONObject();
		object.put("success", 1);
		return object;
	}
	
	public void setUserService(UserService service) {
		this.userService = service;
	}
}
