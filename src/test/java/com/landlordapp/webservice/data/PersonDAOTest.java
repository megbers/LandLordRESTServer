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
import com.landlordapp.webservice.domain.Person;

public class PersonDAOTest {

	@Mock
	HibernateTemplate template;
	@InjectMocks
	private final PersonDAO dao = new PersonDAO();
	private Person person;
	private final Long id = 1001L;

	@Before
	public void doBeforeEachTestCase() {
		person = new Person();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveShouldUpdateTheDatabase() {
		Person actual = dao.save(person);
		verify(template).saveOrUpdate(person);
		assertEquals(person, actual);
	}

	@Test(expected = RuntimeException.class)
	public void saveShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).saveOrUpdate(person);
		dao.save(person);
	}

	@Test
	public void findByIdShouldFindAPerson() {
		when(template.get("com.landlordapp.webservice.domain.Person", id)).thenReturn(person);
		Person actual = dao.findById(id);
		assertEquals(person, actual);
	}

	@Test(expected = RuntimeException.class)
	public void findByIdShouldThrowExcpetionIfRaised() {
		doThrow(new RuntimeException()).when(template).get("com.landlordapp.webservice.domain.Person", id);
		dao.findById(id);
	}

	@Test
	public void deletePersonShouldDeletePerson() {
		dao.delete(person);
		verify(template).delete(person);
	}

	@Test(expected = RuntimeException.class)
	public void deletePersonShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).delete(person);
		dao.delete(person);
	}

	@Test
	public void findAllShouldFindPersons() {
		List<Person> list = new ArrayList<Person>();
		when(template.find("from com.landlordapp.webservice.domain.Person")).thenReturn(list);
		List<Person> actual = dao.findAll();
		verify(template).find("from com.landlordapp.webservice.domain.Person");
		assertEquals(actual, list);
	}

	@Test(expected = RuntimeException.class)
	public void findAllShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.Person");
		dao.findAll();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void findByPropertyShouldReturnListOfExpenses() {
		Long propertyId = 1L;
		List<Person> list = new ArrayList<Person>();
		Object[] values = { propertyId };
		when(template.find("from Person as model where model.property.id= ?", values)).thenReturn(list);
		List<Expense> actual = dao.findByProperty(propertyId);
		verify(template).find("from Person as model where model.property.id= ?", values);
		assertEquals(actual, list);
	}

	@Test(expected = RuntimeException.class)
	public void findByPropertyShouldThrowExceptionIfRaised() {
		Long propertyId = 1L;
		Object[] values = { propertyId };
		doThrow(new RuntimeException()).when(template).find("from Person as model where model.property.id= ?", values);
		dao.findByProperty(propertyId);
	}

}
