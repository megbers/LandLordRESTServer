package com.landlordapp.webservice.data;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.landlordapp.webservice.domain.User;

public class UserDAOTest {
	
	@Mock
	HibernateTemplate template;
	@InjectMocks
	private UserDAO dao = new UserDAO();
	private User user;
	private Long id = 1001L;
	
	@Before
	public void doBeforeEachTestCase() {
		user = new User();
		MockitoAnnotations.initMocks(this);
	} 
	
	@Test
	public void saveShouldUpdateTheDatabase() {
		User actual = dao.save(user);
		verify(template).saveOrUpdate(user);
		assertEquals(user, actual);
	}
	
	@Test(expected = RuntimeException.class) 
	public void saveShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).saveOrUpdate(user);
		dao.save(user);
	}
	
	@Test
	public void findByIdShouldFindAUser() {
		when(template.get("com.landlordapp.webservice.domain.User", id)).thenReturn(user);
		User actual = dao.findById(id);
		assertEquals(user, actual);
	}
	
	@Test(expected = RuntimeException.class) 
	public void findByIdShouldThrowExcpetionIfRaised() {
		doThrow(new RuntimeException()).when(template).get("com.landlordapp.webservice.domain.User", id);
		dao.findById(id);
	}
	
	@Test 
	public void deleteUserShouldDeleteUser() {
		dao.delete(user);
		verify(template).delete(user);
	}
	
	@Test(expected = RuntimeException.class) 
	public void deleteUserShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).delete(user);
		dao.delete(user);
	}
}
