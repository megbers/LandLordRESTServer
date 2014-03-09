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

import com.landlordapp.webservice.domain.Note;

public class NoteDAOTest {
	@Mock
	HibernateTemplate template;
	@InjectMocks
	private final NoteDAO dao = new NoteDAO();
	private Note note;
	private final Long id = 1001L;
	private final String userId = "userId";

	@Before
	public void doBeforeEachTestCase() {
		note = new Note();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveShouldUpdateTheDatabase() {
		Note actual = dao.save(note);
		verify(template).saveOrUpdate(note);
		assertEquals(note, actual);
	}

	@Test(expected = RuntimeException.class)
	public void saveShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).saveOrUpdate(note);
		dao.save(note);
	}

	@Test
	public void findByIdShouldFindANote() {
		List<Note> notes = new ArrayList<Note>();
		notes.add(note);
		Object[] values = {id, userId};
		when(template.find("from com.landlordapp.webservice.domain.Note as model where model.id = ? and model.userId = ?", values)).thenReturn(notes);
		Note actual = dao.findById(id, userId);
		assertEquals(note, actual);
	}
	
	@Test
	public void findByIdShouldNotThrowExceptionWhenNoPropertiesFound() {
		List<Note> notes = new ArrayList<Note>();
		Object[] values = {id, userId};
		when(template.find("from com.landlordapp.webservice.domain.Note as model where model.id = ? and model.userId = ?", values)).thenReturn(notes);
		assertNull(dao.findById(id, userId));
	}
	
	@Test(expected = RuntimeException.class) 
	public void findByIdShouldThrowExcpetionIfRaised() {
		Object[] values = {id, userId};
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.Note as model where model.id = ? and model.userId = ?", values);
		dao.findById(id, userId);
	}
	
	@Test
	public void deleteNoteShouldDeleteNote() {
		dao.delete(note);
		verify(template).delete(note);
	}

	@Test(expected = RuntimeException.class)
	public void deleteNoteShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).delete(note);
		dao.delete(note);
	}

	@Test
	public void findAllShouldFindNotes() {
		List<Note> list = new ArrayList<Note>();
		Object[] values = {userId};
		when(template.find("from com.landlordapp.webservice.domain.Note as model where model.userId = ?", values)).thenReturn(list);
		List<Note> actual = dao.findAll(userId);
		verify(template).find("from com.landlordapp.webservice.domain.Note as model where model.userId = ?", values);
		assertEquals(actual, list);
	}

	@Test(expected = RuntimeException.class)
	public void findAllShouldThrowExceptionIfRaised() {
		Object[] values = {userId};
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.Note as model where model.userId = ?", values);
		dao.findAll(userId);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void findByPropertyShouldReturnListOfNotes() {
		Long propertyId = 1L;
		List<Note> list = new ArrayList<Note>();
		Object[] values = { propertyId, userId };
		when(template.find("from com.landlordapp.webservice.domain.Note as model where model.property.id= ? and model.userId = ?", values)).thenReturn(list);
		List<Note> actual = dao.findByProperty(propertyId, userId);
		verify(template).find("from com.landlordapp.webservice.domain.Note as model where model.property.id= ? and model.userId = ?", values);
		assertEquals(actual, list);
	}

	@Test(expected = RuntimeException.class)
	public void findByPropertyShouldThrowExceptionIfRaised() {
		Long propertyId = 1L;
		new ArrayList<Note>();
		Object[] values = { propertyId, userId };
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.Note as model where model.property.id= ? and model.userId = ?", values);
		dao.findByProperty(propertyId, userId);
	}
}
