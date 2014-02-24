package com.landlordapp.webservice.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "note", catalog = "landlord")
public class Note extends BaseEntity {

	public static final String ID = "id";
	public static final String NOTE_DATE = "date";
	public static final String TEXT = "text";
	public static final String PROPERTY = "property";
	public static final String USER_ID = "userId";

	public Long id;
	public Date date;
	public String text;
	public Property property;
	public String userId;

	public Note() {
	}

	public Note(final JSONObject json) {
		this.id = getLong(json, ID);
		this.date = getDate(json, NOTE_DATE, "yyyy-MM-dd");
		this.text = getString(json, TEXT);
		this.userId = getString(json, USER_ID);

		// TODO Handle Property
		// Should I have the front end submit the entire property, too?
		try {
			this.property = new Property(json.getJSONObject(PROPERTY));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put(ID, id);
		object.put(NOTE_DATE, formatDate(date));
		object.put(TEXT, text);

		if (property != null) {
			object.put(PROPERTY, property.toJSONObject());
		}
		return object;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = ID, unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}
	
	@Column(name = "user_id") 
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	@Column(name = "text")
	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "property_id", nullable = true)
	public Property getProperty() {
		return property;
	}

	public void setProperty(final Property property) {
		this.property = property;
	}

}