package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignInMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		var sc=new Scanner(System.in);
		System.out.print("Enter your email: ");
		String email=sc.nextLine();
		System.out.print("Enter your password: ");
		String password=sc.nextLine();
		User user=userManagementService.getUserByEmail(email);
		if(user!=null&&user.getPassword().equals(password)) {
			context.setLoggedInUser(user);
			System.out.printf("Glad to see you back %s %s\n",user.getFirstName(),user.getLastName() );
		}else{
			System.out.println("Unfortunately, such login and password doesn’t exist");
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** SIGN IN *****");
	}

}
