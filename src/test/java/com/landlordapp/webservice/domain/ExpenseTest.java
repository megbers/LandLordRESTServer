package com.landlordapp.webservice.domain;

import static junit.framework.Assert.assertEquals;

import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class ExpenseTest {
	public static final Long ID = 100L;
	public static final String ID_ST = "100";
	public static final Double AMOUNTTOTAL = 1991D;
	public static final String AMOUNTTOTAL_ST = "1991.0";
	public static final Double AMOUNTPAID = 1893D;
	public static final String AMOUNTPAID_ST = "1893.0";
	public static final Boolean PAID = true;
	public static final String PAID_ST = "true";
	public static final Property PROPERTY = new Property();
	public static final String PROPERTY_ST = "";
	public static final Date ENTEREDDATE = new Date(110, 9, 10);
	public static final String ENTEREDDATE_ST = "10-10-2010";
	public static final Date DUEDATE = new Date(111, 9, 10);
	public static final String DUEDATE_ST = "10-10-2011";
	public static final Date PAIDDATE = new Date(112, 9, 10);
	public static final String PAIDDATE_ST = "10-10-2012";
	
	private JSONObject jsonExpense;
	
	@Before
	public void setUp() throws JSONException {
		jsonExpense = new JSONObject();
		jsonExpense.put(Expense.AMOUNT_PAID, AMOUNTPAID_ST);
		jsonExpense.put(Expense.AMOUNT_TOTAL, AMOUNTTOTAL_ST);
		jsonExpense.put(Expense.DUE_DATE, DUEDATE_ST);
		jsonExpense.put(Expense.ENTERED_DATE, ENTEREDDATE_ST);
		jsonExpense.put(Expense.PAID, PAID_ST);
		jsonExpense.put(Expense.PAID_DATE, PAIDDATE_ST);
	}
	
	@Test 
	public void setterShouldAllWorkAsExpected() {
		Expense expense = new Expense();
		expense.setAmountPaid(AMOUNTPAID);
		expense.setAmountTotal(AMOUNTTOTAL);
		expense.setDueDate(DUEDATE);
		expense.setEnteredDate(ENTEREDDATE);
		expense.setId(ID);
		expense.setPaid(PAID);
		expense.setPaidDate(PAIDDATE);
		expense.setProperty(PROPERTY);
		
		
		assertEquals(expense.getAmountPaid(), AMOUNTPAID);
		assertEquals(expense.getAmountTotal(), AMOUNTTOTAL);
		assertEquals(expense.getDueDate(), DUEDATE);
		assertEquals(expense.getEnteredDate(), ENTEREDDATE);
		assertEquals(expense.getPaid(), PAID);
		assertEquals(expense.getPaidDate(), PAIDDATE);
		assertEquals(expense.getProperty(), PROPERTY);
		assertEquals(expense.getId(), ID);
	}
	
	@Test
	public void contructorShouldPopulateFromJSONObjectWithID() throws JSONException {
		jsonExpense.put("id", ID);
		Expense expense = new Expense(jsonExpense);
		assertEquals(expense.getAmountPaid(), AMOUNTPAID);
		assertEquals(expense.getAmountTotal(), AMOUNTTOTAL);
		assertEquals(expense.getDueDate(), DUEDATE);
		assertEquals(expense.getEnteredDate(), ENTEREDDATE);
		assertEquals(expense.getPaid(), PAID);
		assertEquals(expense.getPaidDate(), PAIDDATE);
		//assertEquals(expense.getProperty(), PROPERTY);
		assertEquals(expense.getId(), ID);
	}
	
	@Test
	public void contructorShouldPopulateFromJSONObjectWithoutID() throws JSONException {
		Expense expense = new Expense(jsonExpense);
		assertEquals(expense.getAmountPaid(), AMOUNTPAID);
		assertEquals(expense.getAmountTotal(), AMOUNTTOTAL);
		assertEquals(expense.getDueDate(), DUEDATE);
		assertEquals(expense.getEnteredDate(), ENTEREDDATE);
		assertEquals(expense.getPaid(), PAID);
		assertEquals(expense.getPaidDate(), PAIDDATE);
		//assertEquals(expense.getProperty(), PROPERTY);
	}
	
	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObject() throws JSONException, IllegalArgumentException, IllegalAccessException {
		jsonExpense.put("id", ID);
		Expense expense = new Expense(jsonExpense);
		JSONObject actual = expense.toJSONObject();
		
		assertEquals(actual.getString(Expense.AMOUNT_PAID), AMOUNTPAID_ST);
		assertEquals(actual.getString(Expense.AMOUNT_TOTAL), AMOUNTTOTAL_ST);
		assertEquals(actual.getString(Expense.DUE_DATE), DUEDATE_ST);
		assertEquals(actual.getString(Expense.ENTERED_DATE), ENTEREDDATE_ST);
		assertEquals(actual.getString(Expense.ID), ID_ST);
		assertEquals(actual.getString(Expense.PAID), PAID_ST);
		assertEquals(actual.getString(Expense.PAID_DATE), PAIDDATE_ST);
	}
	
	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObjectWithPropertyIfPresent() throws JSONException, IllegalArgumentException, IllegalAccessException {
		jsonExpense.put("id", ID);
		Expense expense = new Expense(jsonExpense);
		expense.setProperty(new Property());
		JSONObject actual = expense.toJSONObject();
		
		assertEquals(actual.getJSONObject(Expense.PROPERTY).toString(), "{\"tenants\":[]}");
	}
	
}
