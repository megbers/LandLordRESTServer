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
@Table(name = "location_type", catalog = "landlord")
public class LocationType extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 647189413774299013L;
	
	public static final String ID = "id";
	public static final String NAME = "name";
	public String name;
	public Long id;

	public LocationType() {}
	
	public LocationType(JSONObject json) {
		this.id = getLong(json, ID);
		this.name = getString(json, NAME);
	}
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = ID, unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put(ID, id);
		object.put(NAME, name);
		
		return object;
	}

}
