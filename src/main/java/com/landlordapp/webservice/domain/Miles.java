package com.landlordapp.webservice.domain;

import java.util.Date;

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

import com.landlordapp.webservice.domain.type.MilesType;

@Entity
@Table(name = "miles", catalog = "landlord")
public class Miles extends BaseEntity {

	public static final String ID = "id";
	public static final String PROPERTY = "property";
	public static final String DATE = "milesDate";
	public static final String NUMBER_OF_MILES = "numberOfMiles";
	public static final String MILES_TYPE = "milesType";

	public Long id;
	public Date date;
	public Double numberOfMiles;
	public Property property;
	public MilesType milesType;

	public Miles() {
	}

	public Miles(final JSONObject json) {
		this.id = getLong(json, ID);
		this.date = getDate(json, DATE, "yyyy-MM-dd");
		this.numberOfMiles = getDouble(json, NUMBER_OF_MILES);
		this.milesType = MilesType.valueOf(getString(json, MILES_TYPE));

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
		object.put(DATE, formatDate(date));
		object.put(NUMBER_OF_MILES, numberOfMiles);
		object.put(MILES_TYPE, milesType);

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

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	@Column(name = "number_of_miles")
	public Double getNumberOfMiles() {
		return numberOfMiles;
	}

	public void setNumberOfMiles(final Double numberOfMiles) {
		this.numberOfMiles = numberOfMiles;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "property_id", nullable = true)
	public Property getProperty() {
		return property;
	}

	public void setProperty(final Property property) {
		this.property = property;
	}

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	public MilesType getMilesType() {
		return milesType;
	}

	public void setMilesType(final MilesType milesType) {
		this.milesType = milesType;
	}

}
