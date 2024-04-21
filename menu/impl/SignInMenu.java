package menu.impl;


import configs.ApplicationContext;
import enteties.User;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;

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
