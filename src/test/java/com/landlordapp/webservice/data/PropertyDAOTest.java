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

import com.landlordapp.webservice.domain.Property;

public class PropertyDAOTest {

	@Mock
	HibernateTemplate template;
	@InjectMocks
	private PropertyDAO dao = new PropertyDAO();
	private Property property;
	private Long id = 1001L;
	private String userId = "userId";
	
	@Before
	public void doBeforeEachTestCase() {
		property = new Property();
		MockitoAnnotations.initMocks(this);
	} 
	
	@Test
	public void saveShouldUpdateTheDatabase() {
		Property actual = dao.save(property);
		verify(template).saveOrUpdate(property);
		assertEquals(property, actual);
	}
	
	@Test(expected = RuntimeException.class) 
	public void saveShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).saveOrUpdate(property);
		dao.save(property);
	}
	
	@Test
	public void findByIdShouldFindAUser() {
		when(template.get("com.landlordapp.webservice.domain.Property", id)).thenReturn(property);
		Property actual = dao.findById(id, userId);
		assertEquals(property, actual);
	}
	
	@Test(expected = RuntimeException.class) 
	public void findByIdShouldThrowExcpetionIfRaised() {
		doThrow(new RuntimeException()).when(template).get("com.landlordapp.webservice.domain.Property", id);
		dao.findById(id, userId);
	}
	
	@Test 
	public void deletePropertyShouldDeleteProperty() {
		dao.delete(property);
		verify(template).delete(property);
	}
	
	@Test(expected = RuntimeException.class) 
	public void deletePropertyShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).delete(property);
		dao.delete(property);
	}

	@Test 
	public void findAllShouldFindPropertys() {
		List<Property> list = new ArrayList<Property>();
		Object[] values = { userId };
		when(template.find("from com.landlordapp.webservice.domain.Property as model where model.userId = ?", values)).thenReturn(list);
		List<Property> actual = dao.findAll(userId);
		verify(template).find("from com.landlordapp.webservice.domain.Property as model where model.userId = ?", values);
		assertEquals(actual, list);
	}
	
	@Test(expected = RuntimeException.class) 
	public void findAllShouldThrowExceptionIfRaised() {
		Object[] values = { userId };
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.Property as model where model.userId = ?", values);
		dao.findAll(userId);
	}
	
}
