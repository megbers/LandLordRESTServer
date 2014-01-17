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

import com.landlordapp.webservice.domain.Note;

public class NoteDAOTest {
	@Mock
	HibernateTemplate template;
	@InjectMocks
	private final NoteDAO dao = new NoteDAO();
	private Note Note;
	private final Long id = 1001L;

	@Before
	public void doBeforeEachTestCase() {
		Note = new Note();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveShouldUpdateTheDatabase() {
		Note actual = dao.save(Note);
		verify(template).saveOrUpdate(Note);
		assertEquals(Note, actual);
	}

	@Test(expected = RuntimeException.class)
	public void saveShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).saveOrUpdate(Note);
		dao.save(Note);
	}

	@Test
	public void findByIdShouldFindAUser() {
		when(template.get("com.landlordapp.webservice.domain.Note", id)).thenReturn(Note);
		Note actual = dao.findById(id);
		assertEquals(Note, actual);
	}

	@Test(expected = RuntimeException.class)
	public void findByIdShouldThrowExcpetionIfRaised() {
		doThrow(new RuntimeException()).when(template).get("com.landlordapp.webservice.domain.Note", id);
		dao.findById(id);
	}

	@Test
	public void deleteNoteShouldDeleteNote() {
		dao.delete(Note);
		verify(template).delete(Note);
	}

	@Test(expected = RuntimeException.class)
	public void deleteNoteShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).delete(Note);
		dao.delete(Note);
	}

	@Test
	public void findAllShouldFindNotes() {
		List<Note> list = new ArrayList<Note>();
		when(template.find("from com.landlordapp.webservice.domain.Note")).thenReturn(list);
		List<Note> actual = dao.findAll();
		verify(template).find("from com.landlordapp.webservice.domain.Note");
		assertEquals(actual, list);
	}

	@Test(expected = RuntimeException.class)
	public void findAllShouldThrowExceptionIfRaised() {
		doThrow(new RuntimeException()).when(template).find("from com.landlordapp.webservice.domain.Note");
		dao.findAll();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void findByPropertyShouldReturnListOfNotes() {
		Long propertyId = 1L;
		List<Note> list = new ArrayList<Note>();
		Object[] values = { propertyId };
		when(template.find("from Note as model where model.property.id= ?", values)).thenReturn(list);
		List<Note> actual = dao.findByProperty(propertyId);
		verify(template).find("from Note as model where model.property.id= ?", values);
		assertEquals(actual, list);
	}

	@Test(expected = RuntimeException.class)
	public void findByPropertyShouldThrowExceptionIfRaised() {
		Long propertyId = 1L;
		new ArrayList<Note>();
		Object[] values = { propertyId };
		doThrow(new RuntimeException()).when(template).find("from Note as model where model.property.id= ?", values);
		dao.findByProperty(propertyId);
	}
}
