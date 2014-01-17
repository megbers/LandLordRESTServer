package com.landlordapp.webservice.service.impl;

import static com.landlordapp.webservice.domain.Note.ID;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.landlordapp.webservice.data.NoteDAO;
import com.landlordapp.webservice.domain.Note;
import com.landlordapp.webservice.domain.Property;

public class NoteServiceITest {
	@Mock
	private NoteDAO noteDAO;
	@InjectMocks
	private NoteServiceI service;
	private final String idString = "1001";
	private final Long id = 1001L;
	private Note note;
	private JSONObject jsonNote;

	public Date date;
	public Double numberOfNote;
	public Property property;
	public String noteType;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		property = new Property();
		service = new NoteServiceI();
		note = new Note();
		numberOfNote = 123.45D;
		jsonNote = new JSONObject();
		jsonNote.put("id", idString);
		jsonNote.put("numberOfNote", numberOfNote);
		jsonNote.put("property", property);
		jsonNote.put("noteType", "BANK");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findOneShouldReturnUserWhenOneIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		note.setId(id);
		when(noteDAO.findById(id)).thenReturn(note);

		JSONObject object = service.findOne(idString);
		assertEquals(object.get("id"), id);
	}

	@Test
	public void findOneShouldReturnNullWhenNoUserIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(noteDAO.findById(id)).thenReturn(null);

		JSONObject object = service.findOne(idString);
		assertNull(object);
	}

	@Test
	public void createUserShouldCallSaveUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		note.setId(id);

		when(noteDAO.save(any(Note.class))).thenReturn(note);
		JSONObject actual = service.create(jsonNote);

		assertEquals(actual.get("id"), id);
	}

	@Test
	public void findAllShouldCallFindAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Note> list = new ArrayList<Note>();
		Note note = new Note();
		note.setText("Text");
		note.setProperty(property);
		note.setId(1L);
		list.add(note);

		when(noteDAO.findAll()).thenReturn(list);
		JSONArray actual = service.findAll();

		assertEquals(new Long(1), actual.getJSONObject(0).get("id"));
		assertEquals("Text", actual.getJSONObject(0).getString(Note.TEXT));
	}

	@Test
	public void findByPropertyShouldCallFindAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Long propertyId = 1L;
		List<Note> list = new ArrayList<Note>();
		Note note = new Note();
		note.setText("Text");
		note.setId(1L);
		list.add(note);

		when(noteDAO.findByProperty(propertyId)).thenReturn(list);
		JSONArray actual = service.findByProperty(propertyId);

		assertEquals(new Long(1), actual.getJSONObject(0).get(ID));
		assertEquals("Text", actual.getJSONObject(0).getString(Note.TEXT));
	}

	@Test
	public void updateUserShouldCallSaveUser() throws JSONException, IllegalArgumentException, IllegalAccessException {
		note.setId(id);

		when(noteDAO.save(any(Note.class))).thenReturn(note);
		JSONObject actual = service.update(jsonNote);

		assertEquals(actual.get("id"), id);
	}

	@Test
	public void deleteUserShouldCallDelete() throws JSONException {
		service.delete(jsonNote);
		verify(noteDAO).delete(any(Note.class));
	}
}
