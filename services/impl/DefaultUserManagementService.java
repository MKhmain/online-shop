package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;

import java.util.ArrayList;
import java.util.List;

public class DefaultUserManagementService implements UserManagementService {
	
	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	private static DefaultUserManagementService instance;
	
	private List<User> userDb;
	private int userIndex;
	{
		userDb=new ArrayList<>();
	}
	private DefaultUserManagementService() {
	}
	
	@Override
	public String registerUser(User user) {
		if(user==null){
			return null;
		}
		if(getUserByEmail(user.getEmail())==null){
			if(!user.getEmail().isEmpty()) {
				userDb.add(user);
			}
			else
				return EMPTY_EMAIL_ERROR_MESSAGE;
		}else{
			return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
		}
		return NO_ERROR_MESSAGE;
	}

	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}

	
	@Override
	public List<User> getUsers() {
		return new ArrayList<>(userDb);
	}

	@Override
	public User getUserByEmail(String userEmail) {
		for(User user: userDb){
			if(user!=null && user.getEmail().equalsIgnoreCase(userEmail)){
				return user;
			}
		}
		return null;
	}
	
	void clearServiceState() {
		userDb=new ArrayList<>();
	}
}
