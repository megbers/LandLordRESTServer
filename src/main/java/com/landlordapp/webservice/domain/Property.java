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
@Table(name = "property", catalog = "landlord")
public class Property implements java.io.Serializable {
	private static final long serialVersionUID = -3997672036235921630L;
	private Long id;
	
	public Property(){} 
	
	public Property(JSONObject json) throws JSONException {
		try{
			this.id = json.getLong("id");
		} catch(JSONException e) {
			//Don't need to do anything
		}
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
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("id", id);
		return object;
	}
}
