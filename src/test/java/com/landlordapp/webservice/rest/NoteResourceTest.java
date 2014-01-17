package com.landlordapp.webservice.rest;

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
	private String id;
	private JSONObject fakeNote;

	@Before
	public void doBeforeEachTestCase() throws JSONException {
		id = "Note id";
		fakeNote = new JSONObject();
		fakeNote.put("id", id);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONArray milesList = new JSONArray();
		when(service.findAll()).thenReturn(milesList);
		JSONArray actual = resource.findAllNotes();
		assertEquals(milesList, actual);
	}
	
	@Test
	public void findByPropertyShouldReturnJSONArray() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Long propertyId = 1L;
		JSONArray milesList = new JSONArray();
		when(service.findByProperty(propertyId)).thenReturn(milesList);
		JSONArray actual = resource.findNotesByProperty(propertyId);
		assertEquals(milesList, actual);
	}
	
	
	@Test
	public void findNoteShouldReturnNoteId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(fakeNote);
		JSONObject miles = resource.findNote(id);
		assertEquals(miles.get("id"), id);
	}
	
	@Test
	public void findNoteShouldReturnNullIfNotFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(null);
		JSONObject miles = resource.findNote(id);
		assertNull(miles);
	}
	
	@Test
	public void createNoteShouldReturnNoteId() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.create(fakeNote)).thenReturn(fakeNote);
		JSONObject miles = resource.createNote(fakeNote);
		assertEquals(miles.get("id"), id);
	}
	
	@Test
	public void updateNoteNoteShouldReturnNote() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.update(fakeNote)).thenReturn(fakeNote);
		JSONObject miles = resource.updateNote(fakeNote);
		assertEquals(miles.get("id"), id);
	}
	
	@Test
	public void deleteNoteShouldDeleteNote() throws JSONException, IllegalArgumentException, IllegalAccessException {
		when(service.findOne(id)).thenReturn(fakeNote);
		JSONObject json = resource.deleteNote(id);
		verify(service).delete(fakeNote);
		assertEquals(json.get("success"), 1);
	}
}
