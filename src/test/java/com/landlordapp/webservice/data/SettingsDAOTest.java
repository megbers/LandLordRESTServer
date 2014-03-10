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

import com.landlordapp.webservice.domain.Settings;

public class SettingsDAOTest {
	@Mock
	HibernateTemplate template;
	@InjectMocks
	private final SettingsDAO dao = new SettingsDAO();
	private Settings settings;
	private final Long id = 1001L;
	private final String userId = "userId";

	@Before
	public void doBeforeEachTestCase() {
		settings = new Settings();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveShouldUpdateTheDatabase() {
		Settings actual = dao.save(settings);
		verify(template).saveOrUpdate(settings);
		assertEquals(settings, actual);
	}

	@Test(expected = RuntimeException.class)
	public void saveShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).saveOrUpdate(settings);
		dao.save(settings);
	}

	@Test
	public void findByIdShouldFindASettings() {
		List<Settings> settingsList = new ArrayList<Settings>();
		settingsList.add(settings);
		Object[] values = {id, userId};
		when(template.find("from com.landlordapp.webservice.domain.Settings as model where model.id = ? and model.userId = ?", values)).thenReturn(settingsList);
		Settings actual = dao.findById(id, userId);
		assertEquals(settings, actual);
	}
	
	@Test
	public void findByIdShouldNotThrowExceptionWhenNoPropertiesFound() {
		List<Settings> settingsList = new ArrayList<Settings>();
		Object[] values = {id, userId};
		when(template.find("from com.landlordapp.webservice.domain.Settings as model where model.id = ? and model.userId = ?", values)).thenReturn(settingsList);
		assertNull(dao.findById(id, userId));
	}
	
	@Test(expected = RuntimeException.class) 
	public void findByIdShouldThrowExcpetionIfRaised() {
		Object[] values = {id, userId};
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.Settings as model where model.id = ? and model.userId = ?", values);
		dao.findById(id, userId);
	}
}
