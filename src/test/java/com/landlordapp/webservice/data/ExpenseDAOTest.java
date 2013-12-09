package com.landlordapp.webservice.data;

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
	private ExpenseDAO dao = new ExpenseDAO();
	private Expense Expense;
	private Long id = 1001L;
	
	@Before
	public void doBeforeEachTestCase() {
		Expense = new Expense();
		MockitoAnnotations.initMocks(this);
	} 
	
	@Test
	public void saveShouldUpdateTheDatabase() {
		Expense actual = dao.save(Expense);
		verify(template).saveOrUpdate(Expense);
		assertEquals(Expense, actual);
	}
	
	@Test(expected = RuntimeException.class) 
	public void saveShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).saveOrUpdate(Expense);
		dao.save(Expense);
	}
	
	@Test
	public void findByIdShouldFindAUser() {
		when(template.get("com.landlordapp.webservice.domain.Expense", id)).thenReturn(Expense);
		Expense actual = dao.findById(id);
		assertEquals(Expense, actual);
	}
	
	@Test(expected = RuntimeException.class) 
	public void findByIdShouldThrowExcpetionIfRaised() {
		doThrow(new RuntimeException()).when(template).get("com.landlordapp.webservice.domain.Expense", id);
		dao.findById(id);
	}
	
	@Test 
	public void deleteExpenseShouldDeleteExpense() {
		dao.delete(Expense);
		verify(template).delete(Expense);
	}
	
	@Test(expected = RuntimeException.class) 
	public void deleteExpenseShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).delete(Expense);
		dao.delete(Expense);
	}
	

	@Test 
	public void findAllShouldFindExpenses() {
		List<Expense> list = new ArrayList<Expense>();
		when(template.find("from com.landlordapp.webservice.domain.Expense")).thenReturn(list);
		List<Expense> actual = dao.findAll();
		verify(template).find("from com.landlordapp.webservice.domain.Expense");
		assertEquals(actual, list);
	}
	
	@Test(expected = RuntimeException.class) 
	public void findAllShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.Expense");
		dao.findAll();
	}
	
	@Test
	public void findByPropertyShouldReturnListOfExpenses() {
		Long propertyId = 1L;
		List<Expense> list = new ArrayList<Expense>();
		Object[] values = {propertyId};
		when(template.find("from Expense as model where model.property.id= ?", values)).thenReturn(list);
		List<Expense> actual = dao.findByProperty(propertyId);
		verify(template).find("from Expense as model where model.property.id= ?", values);
		assertEquals(actual, list);
	}
	
	@Test(expected = RuntimeException.class) 
	public void findByPropertyShouldThrowExceptionIfRaised() {
		Long propertyId = 1L;
		List<Expense> list = new ArrayList<Expense>();
		Object[] values = {propertyId};
		doThrow(new RuntimeException()).when(template).find("from Expense as model where model.property.id= ?", values);
		dao.findByProperty(propertyId);
	}
}