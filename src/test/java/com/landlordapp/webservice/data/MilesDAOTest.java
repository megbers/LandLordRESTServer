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

import com.landlordapp.webservice.domain.Miles;

public class MilesDAOTest {
	@Mock
	HibernateTemplate template;
	@InjectMocks
	private MilesDAO dao = new MilesDAO();
	private Miles Miles;
	private Long id = 1001L;
	
	@Before
	public void doBeforeEachTestCase() {
		Miles = new Miles();
		MockitoAnnotations.initMocks(this);
	} 
	
	@Test
	public void saveShouldUpdateTheDatabase() {
		Miles actual = dao.save(Miles);
		verify(template).saveOrUpdate(Miles);
		assertEquals(Miles, actual);
	}
	
	@Test(expected = RuntimeException.class) 
	public void saveShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).saveOrUpdate(Miles);
		dao.save(Miles);
	}
	
	@Test
	public void findByIdShouldFindAUser() {
		when(template.get("com.landlordapp.webservice.domain.Miles", id)).thenReturn(Miles);
		Miles actual = dao.findById(id);
		assertEquals(Miles, actual);
	}
	
	@Test(expected = RuntimeException.class) 
	public void findByIdShouldThrowExcpetionIfRaised() {
		doThrow(new RuntimeException()).when(template).get("com.landlordapp.webservice.domain.Miles", id);
		dao.findById(id);
	}
	
	@Test 
	public void deleteMilesShouldDeleteMiles() {
		dao.delete(Miles);
		verify(template).delete(Miles);
	}
	
	@Test(expected = RuntimeException.class) 
	public void deleteMilesShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).delete(Miles);
		dao.delete(Miles);
	}
	

	@Test 
	public void findAllShouldFindMiless() {
		List<Miles> list = new ArrayList<Miles>();
		when(template.find("from com.landlordapp.webservice.domain.Miles")).thenReturn(list);
		List<Miles> actual = dao.findAll();
		verify(template).find("from com.landlordapp.webservice.domain.Miles");
		assertEquals(actual, list);
	}
	
	@Test(expected = RuntimeException.class) 
	public void findAllShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.Miles");
		dao.findAll();
	}
	
	@Test
	public void findByPropertyShouldReturnListOfMiless() {
		Long propertyId = 1L;
		List<Miles> list = new ArrayList<Miles>();
		Object[] values = {propertyId};
		when(template.find("from Miles as model where model.property.id= ?", values)).thenReturn(list);
		List<Miles> actual = dao.findByProperty(propertyId);
		verify(template).find("from Miles as model where model.property.id= ?", values);
		assertEquals(actual, list);
	}
	
	@Test(expected = RuntimeException.class) 
	public void findByPropertyShouldThrowExceptionIfRaised() {
		Long propertyId = 1L;
		List<Miles> list = new ArrayList<Miles>();
		Object[] values = {propertyId};
		doThrow(new RuntimeException()).when(template).find("from Miles as model where model.property.id= ?", values);
		dao.findByProperty(propertyId);
	}
}
