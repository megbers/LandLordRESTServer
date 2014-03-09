package com.landlordapp.webservice.rest;

import static com.landlordapp.webservice.domain.Expense.USER_ID;
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
	private String userId = "this is the user id";
	private JSONObject fakeExpense;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = "expense id";
		fakeExpense = new JSONObject();
		fakeExpense.put("id", id);
		fakeExpense.put("userId", userId);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray expenses = new JSONArray();
		when(service.findAll(userId)).thenReturn(expenses);
		JSONArray actual = resource.findAllExpenses(userId);
		assertEquals(expenses, actual);
	}

	@Test
	public void findByPropertyShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Long propertyId = 1L;
		JSONArray expenses = new JSONArray();
		when(service.findByProperty(propertyId, userId)).thenReturn(expenses);
		JSONArray actual = resource.findExpensesByProperty(propertyId, userId);
		assertEquals(expenses, actual);
	}

	@Test
	public void findExpenseShouldReturnExpenseId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(fakeExpense);
		JSONObject expense = resource.findExpense(id, userId);
		assertEquals(expense.get("id"), id);
	}

	@Test
	public void findExpenseShouldReturnNullIfNotFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(null);
		JSONObject expense = resource.findExpense(id, userId);
		assertNull(expense);
	}

	@Test
	public void createExpenseShouldReturnExpenseId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.create(fakeExpense)).thenReturn(fakeExpense);
		JSONObject expense = resource.createExpense(fakeExpense, userId);
		assertEquals(expense.get("id"), id);
		assertEquals(expense.get(USER_ID), userId);
	}

	@Test
	public void updateExpenseExpenseShouldReturnExpense() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.update(fakeExpense)).thenReturn(fakeExpense);
		JSONObject expense = resource.updateExpense(fakeExpense, userId);
		assertEquals(expense.get("id"), id);
		assertEquals(expense.get(USER_ID), userId);
	}

	@Test
	public void deleteExpenseShouldDeleteExpense() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(fakeExpense);
		JSONObject json = resource.deleteExpense(id, userId);
		verify(service).delete(fakeExpense);
		assertEquals(json.get("success"), 1);
	}
}
