package com.landlordapp.webservice.service.impl;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.data.NoteDAO;
import com.landlordapp.webservice.domain.Note;
import com.landlordapp.webservice.service.NoteService;

public class NoteServiceI implements NoteService {
	private NoteDAO noteDAO;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public JSONObject findOne(final String id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Note note = noteDAO.findById(Long.parseLong(id));
		if (note == null) {
			return null;
		}
		return note.toJSONObject();
	}

	public JSONObject create(final JSONObject jsonNote) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Note note = new Note(jsonNote);
		return noteDAO.save(note).toJSONObject();
	}

	public JSONObject update(final JSONObject jsonNote) throws JSONException, IllegalArgumentException, IllegalAccessException {
		Note note = new Note(jsonNote);
		return noteDAO.save(note).toJSONObject();
	}

	public void delete(final JSONObject jsonNote) throws JSONException {
		Note note = new Note(jsonNote);
		noteDAO.delete(note);
	}

	public void setNoteDAO(final NoteDAO NoteDAO) {
		this.noteDAO = NoteDAO;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public JSONArray findAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Note> noteList = this.noteDAO.findAll();
		JSONArray noteJSONArray = new JSONArray();
		for (Note note : noteList) {
			noteJSONArray.put(note.toJSONObject());
		}
		return noteJSONArray;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public JSONArray findByProperty(final Long propertyId) throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<Note> noteList = this.noteDAO.findByProperty(propertyId);
		JSONArray noteJSONArray = new JSONArray();
		for (Note note : noteList) {
			noteJSONArray.put(note.toJSONObject());
		}
		return noteJSONArray;
	}
}
