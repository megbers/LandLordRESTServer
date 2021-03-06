package com.landlordapp.webservice.service.impl;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.data.ExpenseDAO;
import com.landlordapp.webservice.domain.Expense;
import com.landlordapp.webservice.service.ExpenseService;

public class ExpenseServiceI implements ExpenseService {

	private ExpenseDAO expenseDAO;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public JSONObject findOne(final Long id, final String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Expense expense = expenseDAO.findById(id, userId);
		if (expense == null) {
			return null;
		}
		return expense.toJSONObject();
	}

	public JSONObject create(final JSONObject jsonExpense) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Expense expense = new Expense(jsonExpense);
		return expenseDAO.save(expense).toJSONObject();
	}

	public JSONObject update(final JSONObject jsonExpense) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Expense expense = new Expense(jsonExpense);
		return expenseDAO.save(expense).toJSONObject();
	}

	public void delete(final JSONObject jsonExpense) throws JSONException {
		Expense expense = new Expense(jsonExpense);
		expenseDAO.delete(expense);
	}

	public void setExpenseDAO(final ExpenseDAO expenseDAO) {
		this.expenseDAO = expenseDAO;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public JSONArray findAll(final String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Expense> expenses = this.expenseDAO.findAll(userId);
		JSONArray expenseJSONArray = new JSONArray();
		for (Expense expense : expenses) {
			expenseJSONArray.put(expense.toJSONObject());
		}
		return expenseJSONArray;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public JSONArray findByProperty(final Long propertyId, final String userId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Expense> expenses = this.expenseDAO.findByProperty(propertyId, userId);
		JSONArray expenseJSONArray = new JSONArray();
		for (Expense expense : expenses) {
			expenseJSONArray.put(expense.toJSONObject());
		}
		return expenseJSONArray;
	}
}
