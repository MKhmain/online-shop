package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;

import java.util.List;

public interface UserManagementService {

	String registerUser(User user);
	
	List<User> getUsers();

	User getUserByEmail(String userEmail);

}
