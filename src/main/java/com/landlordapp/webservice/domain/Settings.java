package com.landlordapp.webservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import com.landlordapp.webservice.domain.type.RentType;

@Entity
@Table(name = "settings", catalog = "landlord")
public class Settings extends BaseEntity {
	
	public static final String ID = "id";
	public static final String USER_ID = "userId";
	public static final String VACANCY = "vacancyRate";
	public static final String ADVERTISER_URL = "advertiserURL";
	public static final String RENT_TYPE = "rentType";
	
	public Long id;
	public String userId;
	public String vacancyRate;
	public String advertiserURL;
	public RentType rentType;

	public Settings() {
	}

	public Settings(JSONObject json) {
		this.id = getLong(json, ID);
		this.userId = getString(json, USER_ID);
		this.vacancyRate = getString(json, VACANCY);
		this.advertiserURL = getString(json, ADVERTISER_URL);
		this.rentType = RentType.valueOf(getString(json, RENT_TYPE));
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
	
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "vacancy_rate")
	public String getVacancyRate() {
		return vacancyRate;
	}

	public void setVacancyRate(String vacancyRate) {
		this.vacancyRate = vacancyRate;
	}

	@Column(name = "advertiser_url")
	public String getAdvertiserURL() {
		return advertiserURL;
	}

	public void setAdvertiserURL(String advertiserURL) {
		this.advertiserURL = advertiserURL;
	}

	@Column(name = "rent_type")
	public RentType getRentType() {
		return rentType;
	}

	public void setRentType(RentType rentType) {
		this.rentType = rentType;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put(ID, id);
		object.put(USER_ID, userId);
		object.put(VACANCY, vacancyRate);
		object.put(ADVERTISER_URL, advertiserURL);
		object.put(RENT_TYPE, rentType);
		return object;
	}
	
}
