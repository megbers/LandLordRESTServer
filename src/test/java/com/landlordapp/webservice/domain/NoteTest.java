package com.landlordapp.webservice.domain;

import static junit.framework.Assert.assertEquals;

import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class NoteTest {
	private static final Long ID = 1L;
	private static final String ID_ST = "1";
	@SuppressWarnings("deprecation")
	private static final Date DATE = new Date(110, 9, 10);
	private static final String DATE_ST = "10-10-2010";
	private static final String DATE_CLIENT_ST = "2010-10-10";
	private static final Long PROPERTY_ID = 300L;
	private static final String PROPERTY_ID_ST = "{id: 300}";
	private static final String TEXT_ST = "Text";
	private static final String USER_ID = "userId";

	private JSONObject jsonNote;

	@Before
	public void setUp() throws JSONException {
		jsonNote = new JSONObject();
		jsonNote.put(Note.NOTE_DATE, DATE_CLIENT_ST);
		jsonNote.put(Note.TEXT, TEXT_ST);
		jsonNote.put(Note.PROPERTY, new JSONObject(PROPERTY_ID_ST));
		jsonNote.put(Note.USER_ID, USER_ID);
	}

	@Test
	public void settersShouldAllWorkAsExpected() {
		Note note = new Note();
		note.setId(ID);
		note.setDate(DATE);
		note.setText(TEXT_ST);
		note.setUserId(USER_ID);
		Property property = new Property();
		property.setId(PROPERTY_ID);
		note.setProperty(property);

		assertEquals(note.getId(), ID);
		assertEquals(note.getDate(), DATE);
		assertEquals(note.getText(), TEXT_ST);
		assertEquals(note.getProperty(), property);
		assertEquals(note.getUserId(), USER_ID);
	}

	@Test
	public void contructorShouldPopulateFromJSONObjectWithID() throws JSONException {
		jsonNote.put("id", ID);
		Note note = new Note(jsonNote);

		assertEquals(note.getId(), ID);
		assertEquals(note.getDate(), DATE);
		assertEquals(note.getText(), TEXT_ST);
		assertEquals(note.getProperty().getId(), PROPERTY_ID);
		assertEquals(note.getUserId(), USER_ID);
	}

	@Test
	public void contructorShouldPopulateFromJSONObjectWithoutID() throws JSONException {
		Note note = new Note(jsonNote);

		assertEquals(note.getDate(), DATE);
		assertEquals(note.getText(), TEXT_ST);
		assertEquals(note.getProperty().getId(), PROPERTY_ID);
		assertEquals(note.getUserId(), USER_ID);
	}

	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObject() throws JSONException, IllegalArgumentException, IllegalAccessException {
		jsonNote.put("id", ID);
		Note note = new Note(jsonNote);
		JSONObject actual = note.toJSONObject();

		assertEquals(actual.getString(Note.ID), ID_ST);
		assertEquals(actual.getString(Note.NOTE_DATE), DATE_ST);
		assertEquals(actual.getString(Note.TEXT), TEXT_ST);
	}

	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObjectWithPropertyIfPresent() throws JSONException, IllegalArgumentException, IllegalAccessException {
		jsonNote.put("id", ID);
		Note note = new Note(jsonNote);
		note.setProperty(new Property());
		JSONObject actual = note.toJSONObject();

		assertEquals(actual.getJSONObject(Note.PROPERTY).toString(), "{\"tenants\":[]}");
	}
}
