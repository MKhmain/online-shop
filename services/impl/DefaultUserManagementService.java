package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultUserManagementService implements UserManagementService {
	
	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	
	private static final int DEFAULT_USERS_CAPACITY = 10;
	
	private static DefaultUserManagementService instance;
	
	private User[] userDb;
	private int userIndex;
	{
		userDb=new User[DEFAULT_USERS_CAPACITY];
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
				if (userIndex >= userDb.length) {
					userDb = Arrays.copyOf(userDb, userDb.length << 1);
				}
				userDb[userIndex++] = user;
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
	public User[] getUsers() {
		int nonNullUsers=0;
		for(User user: userDb){
			if(user!=null){
				nonNullUsers++;
			}
		}
		User[] result=new User[nonNullUsers];
		int idx=0;
		for(User user: userDb){
			if(user!=null){
				result[idx++]=user;
			}
		}
		return result;
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
		userDb=new User[DEFAULT_USERS_CAPACITY];
		userIndex=0;
	}
}
