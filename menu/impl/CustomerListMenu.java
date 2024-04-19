package menu.impl;



import configs.ApplicationContext;
import enteties.User;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;

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
