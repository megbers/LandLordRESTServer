package com.landlordapp.webservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import com.landlordapp.webservice.domain.type.PersonType;

@Entity
@Table(name = "person", catalog = "landlord")
public class Person extends BaseEntity implements Serializable {
	public static final long serialVersionUID = 7431261201492849084L;

	public static final String PROPERTY = "property";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";
	public static final String TYPE = "type";

	public Long id;
	public String name;
	public String phone;
	public String email;
	public Property property;
	public PersonType type;

	public Person() {

	}

	public Person(final JSONObject json) throws JSONException {
		this.id = getLong(json, "id");
		this.name = getString(json, "name");
		this.phone = getString(json, "phone");
		this.email = getString(json, "email");
		this.type = PersonType.valueOf(json.getString("type"));

		// TODO Handle Property
		// Should I have the front end submit the entire property, too?
		try {
			this.property = new Property(json.getJSONObject(PROPERTY));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.property.setId(getLong(json, PROPERTY));
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Enumerated(EnumType.STRING)
	public PersonType getType() {
		return type;
	}

	public void setType(final PersonType type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "property_id", nullable = true)
	public Property getProperty() {
		return property;
	}

	public void setProperty(final Property property) {
		this.property = property;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put(ID, id);
		object.put(NAME, name);
		object.put(PHONE, phone);
		object.put(EMAIL, email);
		object.put(TYPE, type);

//		if (property != null) {
//			object.put(PROPERTY, property.toJSONObject());
//		}

		return object;
	}

}
