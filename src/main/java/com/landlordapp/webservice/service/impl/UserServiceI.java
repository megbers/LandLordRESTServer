package com.landlordapp.webservice.service.impl;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.landlordapp.webservice.data.UserDAO;
import com.landlordapp.webservice.domain.User;
import com.landlordapp.webservice.service.UserService;

public class UserServiceI implements UserService {
	
	private UserDAO userDAO;
	
	public JSONObject findOne(String id) throws JSONException {
		User user = userDAO.findById(Long.parseLong(id));
		if(user == null) {
			return null;
		}
		return user.toJSONObject();
	}
	
	public JSONObject createUser(JSONObject jsonUser) throws JSONException {
		User user = new User(jsonUser);
		return userDAO.save(user).toJSONObject();
	}
	
	public JSONObject updateUser(JSONObject jsonUser) throws JSONException {
		User user = new User(jsonUser);
		return userDAO.save(user).toJSONObject();
	}

	public void deleteUser(JSONObject jsonUser) throws JSONException {
		User user = new User(jsonUser);
		userDAO.delete(user);
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
}
