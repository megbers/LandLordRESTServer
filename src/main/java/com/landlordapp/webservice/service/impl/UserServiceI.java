package com.landlordapp.webservice.service.impl;

import static com.landlordapp.webservice.domain.User.EMAIL;
import static com.landlordapp.webservice.domain.User.PASSWORD;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.landlordapp.webservice.data.UserDAO;
import com.landlordapp.webservice.domain.User;
import com.landlordapp.webservice.service.UserService;

public class UserServiceI implements UserService {
	
	private UserDAO userDAO;
	
	public JSONObject findOne(Long id) throws JSONException, IllegalArgumentException, IllegalAccessException {
		User user = userDAO.findById(id);
		if(user == null) {
			return null;
		}
		return user.toJSONObject();
	}
	
	public JSONObject create(JSONObject jsonUser) throws JSONException, IllegalArgumentException, IllegalAccessException {
		User user = new User(jsonUser);
		return userDAO.save(user).toJSONObject();
	}
	
	public JSONObject update(JSONObject jsonUser) throws JSONException, IllegalArgumentException, IllegalAccessException {
		User user = new User(jsonUser);
		return userDAO.save(user).toJSONObject();
	}

	public void delete(JSONObject jsonUser) throws JSONException {
		User user = new User(jsonUser);
		userDAO.delete(user);
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public JSONArray findAll() throws JSONException, IllegalArgumentException, IllegalAccessException {
		List<User> users = this.userDAO.findAll();
		JSONArray userJSONArray = new JSONArray();
		for(User user: users) {
			userJSONArray.put(user.toJSONObject());
		}
		return userJSONArray;
	}
	
	public JSONObject login(JSONObject jsonUser) throws JSONException {
		String email = jsonUser.getString(EMAIL);
		String password = jsonUser.getString(PASSWORD);
		User user = userDAO.findByEmail(email);
		
		JSONObject response = new JSONObject();
		if(user != null && user.getPassword().equals(password)) {
			response.put("valid", true);
			response.put("user", user.toJSONObject());
		} else {
			response.put("valid", false);
		}
		return response;
	}
	
}
