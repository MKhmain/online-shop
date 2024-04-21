package services.impl;


import enteties.User;
import enteties.impl.DefaultUser;
import services.UserManagementService;
import storage.impl.DefaultUserStoringService;


import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

public class DefaultUserManagementService implements UserManagementService {
	
	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	private static DefaultUserManagementService instance;

	private static DefaultUserStoringService defaultUserStoringService;
	{
		defaultUserStoringService=DefaultUserStoringService.getInstance();
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
				defaultUserStoringService.saveUser(user);
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
		List<User> users=defaultUserStoringService.loadUsers();
		DefaultUser.setCounter(users.stream().mapToInt(s->s.getId()).max().getAsInt());
		return users;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		for(User user: defaultUserStoringService.loadUsers()){
			if (user != null && user.getEmail().equalsIgnoreCase(userEmail)) {
				return user;
			}
		}
		return null;
	}
}
