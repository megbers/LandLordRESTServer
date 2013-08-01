package com.landlordapp.webservice.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class BaseEntityTest {
	private BaseEntity entity;
	private JSONObject json;
	private static final String KEY = "KEY";
	
	@Before
	public void setUp() {
		json = new JSONObject();
		entity = new BaseEntity() {
			@Override
			public JSONObject toJSONObject() throws JSONException {
				return null;
			}
		};
	}
	
	@Test
	public void getStringShouldReturnString() throws JSONException {
		String value = "test";
		json.put(KEY, value);
		assertEquals(entity.getString(json, KEY), value);
	}
	
	@Test
	public void setStringShouldReturnNullIfKeyNotPresent() {
		assertNull(entity.getString(json, KEY));
	}
	
	@Test
	public void getLongShouldReturnLong() throws JSONException {
		Long value = new Long(101);
		json.put(KEY, value);
		assertEquals(entity.getLong(json, KEY), value);
	}
	
	@Test
	public void setLongShouldReturnNullIfKeyNotPresent() {
		assertNull(entity.getLong(json, KEY));
	}
	
	@Test
	public void getDoubleShouldReturnDouble() throws JSONException {
		Double value = new Double(101.1);
		json.put(KEY, value);
		assertEquals(entity.getDouble(json, KEY), value);
	}
	
	@Test
	public void setDoubleShouldReturnNullIfKeyNotPresent() {
		assertNull(entity.getDouble(json, KEY));
	}
	
	@Test
	public void getBooleanShouldReturnBoolean() throws JSONException {
		Boolean value = new Boolean(true);
		json.put(KEY, value);
		assertEquals(entity.getBoolean(json, KEY), value);
	}
	
	@Test
	public void setBooleanShouldReturnNullIfKeyNotPresent() {
		assertNull(entity.getBoolean(json, KEY));
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public void getDateShouldReturnDate() throws JSONException {
		String value = "10-10-2010";
		Date date = new Date(110, 9, 10);
		json.put(KEY, value);
		assertEquals(entity.getDate(json, KEY), date);
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public void getDateShouldFormatDateWithPassedInFormat() throws JSONException {
		String value = "2010-12-10";
		Date date = new Date(110, 11, 10);
		json.put(KEY, value);
		assertEquals(entity.getDate(json, KEY, "yyyy-MM-dd"), date);
	}
	
	
	@Test
	@SuppressWarnings("deprecation")
	public void formateDateShouldReturnDateWithDefaultValue() throws JSONException {
		String value = "10-10-2010";
		Date date = new Date(110, 9, 10);
		json.put(KEY, value);
		assertEquals(entity.formatDate(date), value);
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public void formateDateShouldFormatDateWithPassedInFormat() throws JSONException {
		String value = "2010-12-10";
		Date date = new Date(110, 11, 10);
		json.put(KEY, value);
		assertEquals(entity.formatDate(date, "yyyy-MM-dd"), value);
	}
	
	
	@Test
	public void setvShouldReturnNullIfKeyNotPresent() {
		assertNull(entity.getDate(json, KEY));
	}

}