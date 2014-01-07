package com.landlordapp.webservice.service.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.landlordapp.webservice.data.ExpenseDAO;
import com.landlordapp.webservice.domain.Expense;

public class ExpenseServiceITest {
	@Mock
	private ExpenseDAO expenseDAO;
	@InjectMocks
	private ExpenseServiceI service;
	private final String idString = "1001";
	private final String amountPaid = "1234.56";
	private final String amountTotal = "7890.0";
	private final Long id = 1001L;
	private Expense expense;
	private JSONObject jsonExpense;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		service = new ExpenseServiceI();
		expense = new Expense();
		jsonExpense = new JSONObject();
		jsonExpense.put(Expense.ID, idString);
		jsonExpense.put(Expense.AMOUNT_TOTAL, amountTotal);
		jsonExpense.put(Expense.AMOUNT_PAID, amountPaid);
		jsonExpense.put(Expense.EXPENSE_TYPE, "RENT");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findOneShouldReturnUserWhenOneIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		expense.setId(id);
		when(expenseDAO.findById(id)).thenReturn(expense);

		JSONObject object = service.findOne(idString);
		assertEquals(object.get(Expense.ID), id);
	}

	@Test
	public void findOneShouldReturnNullWhenNoUserIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(expenseDAO.findById(id)).thenReturn(null);

		JSONObject object = service.findOne(idString);
		assertNull(object);
	}

	@Test
	public void createUserShouldCallSaveUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		expense.setId(id);

		when(expenseDAO.save(any(Expense.class))).thenReturn(expense);
		JSONObject actual = service.create(jsonExpense);

		assertEquals(actual.get(Expense.ID), id);
	}

	@Test
	public void findAllShouldCallFindAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Expense> list = new ArrayList<Expense>();
		Expense expense = new Expense();
		expense.setAmountTotal(7890D);
		expense.setAmountPaid(1234.56D);
		expense.setId(1L);
		list.add(expense);

		when(expenseDAO.findAll()).thenReturn(list);
		JSONArray actual = service.findAll();

		assertEquals(new Long(1), actual.getJSONObject(0).get(Expense.ID));
		assertEquals(amountTotal, actual.getJSONObject(0).getString(Expense.AMOUNT_TOTAL));
		assertEquals(amountPaid, actual.getJSONObject(0).getString(Expense.AMOUNT_PAID));
	}

	@Test
	public void findByPropertyShouldCallFindAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Long propertyId = 1L;
		List<Expense> list = new ArrayList<Expense>();
		Expense expense = new Expense();
		expense.setAmountTotal(7890D);
		expense.setAmountPaid(1234.56D);
		expense.setId(1L);
		list.add(expense);

		when(expenseDAO.findByProperty(propertyId)).thenReturn(list);
		JSONArray actual = service.findByProperty(propertyId);

		assertEquals(new Long(1), actual.getJSONObject(0).get(Expense.ID));
		assertEquals(amountTotal, actual.getJSONObject(0).getString(Expense.AMOUNT_TOTAL));
		assertEquals(amountPaid, actual.getJSONObject(0).getString(Expense.AMOUNT_PAID));
	}

	@Test
	public void updateUserShouldCallSaveUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		expense.setId(id);

		when(expenseDAO.save(any(Expense.class))).thenReturn(expense);
		JSONObject actual = service.update(jsonExpense);

		assertEquals(actual.get(Expense.ID), id);
	}

	@Test
	public void deleteUserShouldCallDelete() throws JSONException {
		service.delete(jsonExpense);
		verify(expenseDAO).delete(any(Expense.class));
	}
}
