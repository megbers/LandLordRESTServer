package com.landlordapp.webservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user", catalog = "landlord")
public class User extends BaseEntity implements Serializable {
	public  static final long serialVersionUID = -5570961237967000666L;
	//public  static final String[] fieldNames = {"id", "email", "password"};
	public  Long id;
	public  String email;
	public  String password;
	
	public User() { }
	
	public User(JSONObject json) throws JSONException {
		this.id = getLong(json, "id");
		this.email = getString(json, "email");
		this.password = getString(json, "password");
	}
	
	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("email", email);
		object.put("password", password);
		return object;
	}
}
