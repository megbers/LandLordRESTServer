package com.landlordapp.webservice.domain;

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
public class User implements java.io.Serializable {
	private static final long serialVersionUID = -5570961237967000666L;
	//private static final String[] fieldNames = {"id", "email", "password"};
	private Long id;
	private String email;
	private String password;
	
	public User() {
		
	}
	
	public User(JSONObject json) throws JSONException {
		try{
			this.id = json.getLong("id");
		} catch(JSONException e) {
			//Don't need to do anything
		}
		this.email = json.getString("email");
		this.password = json.getString("password");
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
