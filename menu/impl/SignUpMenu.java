package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.impl.DefaultUser;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignUpMenu implements Menu {

	private UserManagementService userManagementService;
	private ApplicationContext context;

	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		if(context.getLoggedInUser()!=null){
			System.out.println("You already signed in");
			return;
		}
		printMenuHeader();
		var sc=new Scanner(System.in);
		System.out.print("First Name: ");
		String fName=sc.next();
		System.out.print("Last Name: ");
		String lName=sc.next();
		System.out.print("Password: ");
		String password=sc.next();
		System.out.print("Email: ");
		String email= sc.next();
		User user=new DefaultUser(fName,lName,password,email);
		String result=userManagementService.registerUser(user);
		if(result.isEmpty()||result==null){
			context.setLoggedInUser(user);
			System.out.println("New user is created");
		}else{
			System.out.println(result);
		}

	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** SIGN UP *****");
	}

}
