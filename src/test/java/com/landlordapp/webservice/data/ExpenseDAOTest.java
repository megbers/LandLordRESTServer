package com.landlordapp.webservice.data;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.landlordapp.webservice.domain.Expense;

public class ExpenseDAOTest {

	@Mock
	HibernateTemplate template;
	@InjectMocks
	private final ExpenseDAO dao = new ExpenseDAO();
	private Expense expense;
	private final Long id = 1001L;
	private final String userId = "userId";

	@Before
	public void doBeforeEachTestCase() {
		expense = new Expense();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveShouldUpdateTheDatabase() {
		Expense actual = dao.save(expense);
		verify(template).saveOrUpdate(expense);
		assertEquals(expense, actual);
	}

	@Test(expected = RuntimeException.class)
	public void saveShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).saveOrUpdate(expense);
		dao.save(expense);
	}

	@Test
	public void findByIdShouldFindAExpense() {
		List<Expense> expenses = new ArrayList<Expense>();
		expenses.add(expense);
		Object[] values = {id, userId};
		when(template.find("from com.landlordapp.webservice.domain.Expense as model where model.id = ? and model.userId = ?", values)).thenReturn(expenses);
		Expense actual = dao.findById(id, userId);
		assertEquals(expense, actual);
	}
	
	@Test
	public void findByIdShouldNotThrowExceptionWhenNoPropertiesFound() {
		List<Expense> expenses = new ArrayList<Expense>();
		Object[] values = {id, userId};
		when(template.find("from com.landlordapp.webservice.domain.Expense as model where model.id = ? and model.userId = ?", values)).thenReturn(expenses);
		assertNull(dao.findById(id, userId));
	}
	
	@Test(expected = RuntimeException.class) 
	public void findByIdShouldThrowExcpetionIfRaised() {
		Object[] values = {id, userId};
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.Expense as model where model.id = ? and model.userId = ?", values);
		dao.findById(id, userId);
	}

	@Test
	public void deleteExpenseShouldDeleteExpense() {
		dao.delete(expense);
		verify(template).delete(expense);
	}

	@Test(expected = RuntimeException.class)
	public void deleteExpenseShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).delete(expense);
		dao.delete(expense);
	}

	@Test
	public void findAllShouldFindExpenses() {
		List<Expense> list = new ArrayList<Expense>();
		Object[] values = {userId};
		when(template.find("from Expense as model where model.userId = ?", values)).thenReturn(list);
		List<Expense> actual = dao.findAll(userId);
		verify(template).find("from Expense as model where model.userId = ?", values);
		assertEquals(actual, list);
	}

	@Test(expected = RuntimeException.class)
	public void findAllShouldThrowExceptionIfRaised() {
		Object[] values = {userId};
		doThrow(new RuntimeException()).when(template).find("from Expense as model where model.userId = ?", values);
		dao.findAll(userId);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void findByPropertyShouldReturnListOfExpenses() {
		Long propertyId = 1L;
		List<Expense> list = new ArrayList<Expense>();
		Object[] values = {propertyId, userId};
		when(template.find("from Expense as model where model.property.id = ? and model.userId = ?", values)).thenReturn(list);
		List<Expense> actual = dao.findByProperty(propertyId, userId);
		verify(template).find("from Expense as model where model.property.id = ? and model.userId = ?", values);
		assertEquals(actual, list);
	}

	@Test(expected = RuntimeException.class)
	public void findByPropertyShouldThrowExceptionIfRaised() {
		Long propertyId = 1L;
		new ArrayList<Expense>();
		Object[] values = {propertyId, userId};
		doThrow(new RuntimeException()).when(template).find("from Expense as model where model.property.id = ? and model.userId = ?", values);
		dao.findByProperty(propertyId, userId);
	}
}
