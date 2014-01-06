package com.landlordapp.webservice.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.landlordapp.webservice.service.impl.ExpenseServiceI;

public class ExpenseResourceTest {
	@Mock
	ExpenseServiceI service;
	@InjectMocks
	private final ExpenseResource resource = new ExpenseResource();
	private String id;
	private JSONObject fakeExpense;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = "expense id";
		fakeExpense = new JSONObject();
		fakeExpense.put("id", id);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray expenses = new JSONArray();
		when(service.findAll()).thenReturn(expenses);
		JSONArray actual = resource.findAllExpenses();
		assertEquals(expenses, actual);
	}

	@Test
	public void findByPropertyShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Long propertyId = 1L;
		JSONArray expenses = new JSONArray();
		when(service.findByProperty(propertyId)).thenReturn(expenses);
		JSONArray actual = resource.findExpensesByProperty(propertyId);
		assertEquals(expenses, actual);
	}

	@Test
	public void findExpenseShouldReturnExpenseId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(fakeExpense);
		JSONObject expense = resource.findExpense(id);
		assertEquals(expense.get("id"), id);
	}

	@Test
	public void findExpenseShouldReturnNullIfNotFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(null);
		JSONObject expense = resource.findExpense(id);
		assertNull(expense);
	}

	@Test
	public void createExpenseShouldReturnExpenseId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.create(fakeExpense)).thenReturn(fakeExpense);
		JSONObject expense = resource.createExpense(fakeExpense);
		assertEquals(expense.get("id"), id);
	}

	@Test
	public void updateExpenseExpenseShouldReturnExpense() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.update(fakeExpense)).thenReturn(fakeExpense);
		JSONObject expense = resource.updateExpense(fakeExpense);
		assertEquals(expense.get("id"), id);
	}

	@Test
	public void deleteExpenseShouldDeleteExpense() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(fakeExpense);
		JSONObject json = resource.deleteExpense(id);
		verify(service).delete(fakeExpense);
		assertEquals(json.get("success"), 1);
	}
}
