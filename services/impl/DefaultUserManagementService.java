package services.impl;


import enteties.User;
import enteties.impl.DefaultUser;
import services.UserManagementService;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.*;

public class DefaultUserManagementService implements UserManagementService {
	
	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	private static DefaultUserManagementService instance;
	private static final Path path = Path.of("resources/userDb.txt");

	private List<User> userDb;
	{
		userDb=new ArrayList<>();
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
				StringBuilder stb=new StringBuilder();
				stb.append(user.getFirstName()+" ")
					.append(user.getLastName()+" ")
					.append(user.getPassword()+" ")
					.append(user.getEmail()+System.lineSeparator());
				try {
					Files.write(path,stb.toString().getBytes(),APPEND,WRITE);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				//userDb.add(user);
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
		try {
			return Files.lines(path)
					.map(s-> s.split(System.lineSeparator()))
					.flatMap(Arrays::stream)
					.map(s->s.split(" "))
					.map(s->new DefaultUser(s[0],s[1],s[2],s[3]))
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User getUserByEmail(String userEmail) {

		try {
			return Files.lines(path)
					.map(s -> s.split(System.lineSeparator()))
					.flatMap(Arrays::stream)
					.map(s -> s.split(" "))
					.map(s -> new DefaultUser(s[0], s[1], s[2], s[3]))
					.filter(u -> u.getEmail().equalsIgnoreCase(userEmail))
					.findFirst().orElse(null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

//		userDb.forEach(System.out::println);
//		return userDb.stream().
//				filter(Objects::nonNull).
//				filter(u->u.getEmail().equalsIgnoreCase(userEmail)).
//				findFirst().
//				orElse(null);
//		for(User user: userDb){
//			if(user!=null && user.getEmail().equalsIgnoreCase(userEmail)){
//				return user;
//			}
//		}
//		return null;
	}
	
	void clearServiceState() {
		userDb=new ArrayList<>();
	}
}
