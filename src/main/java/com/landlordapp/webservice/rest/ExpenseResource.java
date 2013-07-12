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

import com.landlordapp.webservice.service.ExpenseService;

@Component
@Path("expense")
public class ExpenseResource {
	@Autowired
	private ExpenseService expenseService;
	
	@GET
	@Path("findAll")
	@Produces(APPLICATION_JSON)
	public JSONArray findAllExpenses() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray response = expenseService.findAll();
		return response;
	}
	
	@GET
	@Path("find/{id}")
	@Produces(APPLICATION_JSON)
	public JSONObject findExpense(@PathParam("id") String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject response = expenseService.findOne(id);
		return response;
	}
	
	@PUT
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject createExpense(JSONObject expense) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject newExpense = expenseService.create(expense);
		return newExpense;
	}
	
	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JSONObject updateExpense(JSONObject expense) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject newExpense = expenseService.update(expense);
		return newExpense;
	}
	
	@DELETE
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_FORM_URLENCODED)
	public JSONObject deleteExpense(@FormParam("id") String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject expense = expenseService.findOne(id);
		expenseService.delete(expense);
		JSONObject object = new JSONObject();
		object.put("success", 1);
		return object;
	}
	
	public void setExpenseService(ExpenseService service) {
		this.expenseService = service;
	}
}

