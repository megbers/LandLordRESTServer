package com.landlordapp.webservice.domain;

import java.io.Serializable;

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
@Table(name = "person", catalog = "landlord")
public class Person extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 7431261201492849084L;
	private Long id;
	private String name;
	private String phone;
	private String email;
	private Property property;
	
	public Person() {
		
	}
	
	public Person(JSONObject json) throws JSONException {
		try{
			this.id = json.getLong("id");
		} catch(JSONException e) {
			//Don't need to do anything
		}
		this.name = json.getString("name");
		this.phone = json.getString("phone");
		this.email = json.getString("email");
	}
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "phone", length = 50)
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "property_id", nullable = true)
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("name", name);
		object.put("phone", phone);
		object.put("email", email);
		return object;
	}

}
