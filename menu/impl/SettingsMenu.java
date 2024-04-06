package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;

import java.util.Scanner;

public class SettingsMenu implements Menu {

	private static final String SETTINGS = "1. Change Password" + System.lineSeparator()
			+ "2. Change Email";

	private final ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		Menu mainMenu=new MainMenu();
		printMenuHeader();
		if(context.getLoggedInUser()==null){
			System.out.println("Please, log in or create new account to change your account settings");
		}else{
			System.out.println(SETTINGS);
			var sc=new Scanner(System.in);
			System.out.print("User input: ");
			String input=sc.nextLine();
			if(input.equalsIgnoreCase("menu")){
				new MainMenu().start();
			}else{
				int option= Integer.parseInt(input);
				switch(option){
					case 1->mainMenu=new ChangePasswordMenu();
					case 2->mainMenu=new ChangeEmailMenu();
					default->{
						System.out.println("Only 1, 2 is allowed. Try one more time");
						mainMenu=new SettingsMenu();
					}
				}
			}
		}
		mainMenu.start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** SETTINGS *****");
	}

}
