package com.landlordapp.webservice.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public abstract class BaseEntity {
	public String getString(JSONObject json, String key) {
		try {
			return json.getString(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	public Long getLong(JSONObject json, String key) {
		try {
			return json.getLong(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	public Double getDouble(JSONObject json, String key) {
		try {
			return json.getDouble(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	public Boolean getBoolean(JSONObject json, String key) {
		try {
			return json.getBoolean(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	public Date getDate(JSONObject json, String key) {
		return getDate(json, key, "dd-MM-yyyy");
	}
	
	public Date getDate(JSONObject json, String key, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(json.getString(key));
		} catch (Exception e) {
			return null;
		}
	}
	
	protected String formatDate(Date date, String dateFormate) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
		try {
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	protected String formatDate(Date date) {
		return formatDate(date, "dd-MM-yyyy");
	}
	
	public abstract JSONObject toJSONObject() throws JSONException;
	
//	public JSONObject toJSONObject() throws JSONException, IllegalArgumentException, IllegalAccessException {
//		JSONObject object = new JSONObject();
//		for(Field field : this.getClass().getDeclaredFields()) {
//			if (!Modifier.isStatic(field.getModifiers())) {
//				String name = field.getName();
//				Object value = formatField(field);
//				System.out.println(name + " :: " + value);
//				object.put(name, value);
//			}
//		}
//		
//		return object;
//	}
//	
//	private Object formatField(Field field) throws IllegalArgumentException, IllegalAccessException, JSONException {
//		Object value = field.get(this);
//		if(field.getType() == Date.class) {
//			value = formatDate((Date)value);
//		} else if(field.getType().getSuperclass() == BaseEntity.class && value != null) {
//			value = ((BaseEntity) value).toJSONObject();
//		} else if(field.getType() == Set.class && value != null) {
//			JSONArray array = new JSONArray();
//			Set collection = (Set) value;
//			Iterator iterator = collection.iterator();
//			while(iterator.hasNext()) {
//				array.put(((BaseEntity) iterator.next()).toJSONObject());
//			}
//			
//			return array;
//		}
//		
//		return value;
//	}
	
}
