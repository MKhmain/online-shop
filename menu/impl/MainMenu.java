package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.Main;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;

import java.util.Scanner;

public class MainMenu implements Menu {

	public static final String MENU_COMMAND = "menu";
	private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed." + System.lineSeparator()
			+ "1. Sign Up" + System.lineSeparator() + "2. Sign In"
			+ System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
			+ "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() + 
			"6. Customer List";

	private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed." + System.lineSeparator()
			+ "1. Sign Up" + System.lineSeparator() + "2. Sign Out"
			+ System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
			+ "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() + 
			"6. Customer List";;

	private ApplicationContext context;
	
	{
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {

		while(true){
			if(context.getMainMenu()==null){
				context.setMainMenu(this);
			}
			Menu menu=null;
			boolean flag=true;
			while(flag) {

				printMenuHeader();
				System.out.print("User input: ");
				var sc = new Scanner(System.in);
				String input = sc.nextLine();
				if(input.equalsIgnoreCase(Main.EXIT_COMMAND)){
					System.exit(0);
				}
				menu = switch (input) {
					case "1" -> {
						flag = false;
						yield new SignUpMenu();
					}
					case "2" -> {
						flag = false;
						if (context.getLoggedInUser() == null)
							yield new SignInMenu();
						yield new SignOutMenu();
					}
					case "3" -> {
						flag = false;
						yield new ProductCatalogMenu();
					}
					case "4" -> {
						flag = false;
						yield new MyOrdersMenu();
					}
					case "5" -> {
						flag = false;
						yield new SettingsMenu();
					}
					case "6" -> {
						flag = false;
						yield new CustomerListMenu();
					}

					default -> {
						System.out.println("Only 1, 2, 3, 4, 5 is allowed. Try one more time");
						yield null;
					}
				};
			}
			menu.start();
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MAIN MENU *****");
		if(context.getLoggedInUser()==null)
			System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
		else
			System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
	}

}
