package com.landlordapp.webservice.service.impl;

import static com.landlordapp.webservice.domain.Note.USER_ID;
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
	private final String userId = "This is the user id";
	public Date date;
	public Property property;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		property = new Property();
		service = new NoteServiceI();
		note = new Note();
		jsonNote = new JSONObject();
		jsonNote.put("id", idString);
		jsonNote.put("property", property);
		jsonNote.put("text", "TEXT");
		jsonNote.put(USER_ID, userId);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findOneShouldReturnNoteWhenOneIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		note.setId(id);
		when(noteDAO.findById(id, userId)).thenReturn(note);

		JSONObject object = service.findOne(id, userId);
		assertEquals(object.get("id"), id);
	}

	@Test
	public void findOneShouldReturnNullWhenNoNoteIsFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(noteDAO.findById(id, userId)).thenReturn(null);

		JSONObject object = service.findOne(id, userId);
		assertNull(object);
	}

	@Test
	public void createNoteShouldCallSaveNote() throws JSONException, IllegalArgumentException, IllegalAccessException {
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

		when(noteDAO.findAll(userId)).thenReturn(list);
		JSONArray actual = service.findAll(userId);

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

		when(noteDAO.findByProperty(propertyId, userId)).thenReturn(list);
		JSONArray actual = service.findByProperty(propertyId, userId);

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
