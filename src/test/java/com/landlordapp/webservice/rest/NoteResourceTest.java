package com.landlordapp.webservice.rest;

import static com.landlordapp.webservice.domain.Note.USER_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.landlordapp.webservice.service.impl.NoteServiceI;

public class NoteResourceTest {
	@Mock
	NoteServiceI service;
	@InjectMocks
	private NoteResource resource = new NoteResource();
	private Long id;
	private JSONObject fakeNote;
	private String userId = "This is the userId";

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = 432L;
		fakeNote = new JSONObject();
		fakeNote.put("id", id);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray noteList = new JSONArray();
		when(service.findAll(userId)).thenReturn(noteList);
		JSONArray actual = resource.findAllNotes(userId);
		assertEquals(noteList, actual);
	}
	
	@Test
	public void findByPropertyShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Long propertyId = 1L;
		JSONArray noteList = new JSONArray();
		when(service.findByProperty(propertyId, userId)).thenReturn(noteList);
		JSONArray actual = resource.findNotesByProperty(propertyId, userId);
		assertEquals(noteList, actual);
	}
	
	
	@Test
	public void findNoteShouldReturnNoteId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(fakeNote);
		JSONObject note = resource.findNote(id, userId);
		assertEquals(note.get("id"), id);
	}
	
	@Test
	public void findNoteShouldReturnNullIfNotFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(null);
		JSONObject note = resource.findNote(id, userId);
		assertNull(note);
	}
	
	@Test
	public void createNoteShouldReturnNoteId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.create(fakeNote)).thenReturn(fakeNote);
		JSONObject note = resource.createNote(fakeNote, userId);
		assertEquals(note.get("id"), id);
		assertEquals(note.get(USER_ID), userId);
	}
	
	@Test
	public void updateNoteNoteShouldReturnNote() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.update(fakeNote)).thenReturn(fakeNote);
		JSONObject note = resource.updateNote(fakeNote, userId);
		assertEquals(note.get("id"), id);
		assertEquals(note.get(USER_ID), userId);
	}
	
	@Test
	public void deleteNoteShouldDeleteNote() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id, userId)).thenReturn(fakeNote);
		JSONObject json = resource.deleteNote(id, userId);
		verify(service).delete(fakeNote);
		assertEquals(json.get("success"), 1);
	}
}
