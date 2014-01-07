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

import com.landlordapp.webservice.domain.User;

public class UserDAOTest {

	@Mock
	HibernateTemplate template;
	@InjectMocks
	private final UserDAO dao = new UserDAO();
	private User user;
	private final Long id = 1001L;

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

	@Test
	public void findAllShouldFindUsers() {
		List<User> list = new ArrayList<User>();
		when(template.find("from com.landlordapp.webservice.domain.User")).thenReturn(list);
		List<User> actual = dao.findAll();
		verify(template).find("from com.landlordapp.webservice.domain.User");
		assertEquals(actual, list);
	}

	@Test(expected = RuntimeException.class)
	public void findAllShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.User");
		dao.findAll();
	}

	@Test
	public void findByEmailShouldReturnUser() {
		String email = "email@email.com";
		List<User> list = new ArrayList<User>();
		list.add(user);
		Object[] values = { email };
		when(template.find("from com.landlordapp.webservice.domain.User as model where model.email=?", values)).thenReturn(list);
		User actual = dao.findByEmail(email);
		verify(template).find("from com.landlordapp.webservice.domain.User as model where model.email=?", values);
		assertEquals(actual, user);
	}

	@Test
	public void findByEmailShouldReturnNullWhenNoUserFound() {
		String email = "email@email.com";
		List<User> list = new ArrayList<User>();
		Object[] values = { email };
		when(template.find("from com.landlordapp.webservice.domain.User as model where model.email=?", values)).thenReturn(list);
		User actual = dao.findByEmail(email);
		verify(template).find("from com.landlordapp.webservice.domain.User as model where model.email=?", values);
		assertEquals(actual, null);
	}

	@Test(expected = RuntimeException.class)
	public void findByEmailShouldThrowExceptionIfRaised() {
		String email = "email@email.com";
		Object[] values = { email };
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.User as model where model.email=?", values);
		dao.findByEmail(email);
	}

}
