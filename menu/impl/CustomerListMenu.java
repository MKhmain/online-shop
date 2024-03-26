package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultUserManagementService;

public class CustomerListMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;
	
	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		if(context.getLoggedInUser()==null){
			System.out.println("Please, log in or create new account to see customer list");
		}else{
			displayUsers();
		}
	}
	public void displayUsers(){
		for(User user: userManagementService.getUsers()){
			System.out.println(user);
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** CUSTOMER LIST *****");
	}

}
