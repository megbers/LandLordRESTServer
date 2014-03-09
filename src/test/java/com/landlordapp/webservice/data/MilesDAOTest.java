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
	private final MilesDAO dao = new MilesDAO();
	private Miles Miles;
	private final Long id = 1001L;
	private final String userId = "userId";

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
	public void findByIdShouldFindAMiles() {
		when(template.get("com.landlordapp.webservice.domain.Miles", id)).thenReturn(Miles);
		Miles actual = dao.findById(id, userId);
		assertEquals(Miles, actual);
	}

	@Test(expected = RuntimeException.class)
	public void findByIdShouldThrowExcpetionIfRaised() {
		doThrow(new RuntimeException()).when(template).get("com.landlordapp.webservice.domain.Miles", id);
		dao.findById(id, userId);
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
		Object[] values = {userId};
		when(template.find("from com.landlordapp.webservice.domain.Miles as model where model.userId = ?", values)).thenReturn(list);
		List<Miles> actual = dao.findAll(userId);
		verify(template).find("from com.landlordapp.webservice.domain.Miles as model where model.userId = ?", values);
		assertEquals(actual, list);
	}

	@Test(expected = RuntimeException.class)
	public void findAllShouldThrowExceptionIfRaised() {
		Object[] values = {userId};
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.Miles as model where model.userId = ?", values);
		dao.findAll(userId);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void findByPropertyShouldReturnListOfMiless() {
		Long propertyId = 1L;
		List<Miles> list = new ArrayList<Miles>();
		Object[] values = { propertyId, userId };
		when(template.find("from Miles as model where model.property.id= ? and model.userId = ?", values)).thenReturn(list);
		List<Miles> actual = dao.findByProperty(propertyId, userId);
		verify(template).find("from Miles as model where model.property.id= ? and model.userId = ?", values);
		assertEquals(actual, list);
	}

	@Test(expected = RuntimeException.class)
	public void findByPropertyShouldThrowExceptionIfRaised() {
		Long propertyId = 1L;
		new ArrayList<Miles>();
		Object[] values = { propertyId, userId };
		doThrow(new RuntimeException()).when(template).find("from Miles as model where model.property.id= ? and model.userId = ?", values);
		dao.findByProperty(propertyId, userId);
	}
}
