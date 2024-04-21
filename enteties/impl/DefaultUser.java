package enteties.impl;

import enteties.User;

public class DefaultUser implements User {

	private static int userCounter=0;
	private  int id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	{
		id=++userCounter;
	}
	public DefaultUser() {
	}

	public DefaultUser(String firstName, String lastName, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public DefaultUser(int id, String firstName, String lastName, String password, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "DefaultUser{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email= '"+ email+'\'' +
				'}';
	}

	@Override
	public void setPassword(String password) {
		if(password==null){
			return;
		}
		this.password=password;
	}

	@Override
	public void setEmail(String newEmail) {
		if(newEmail==null)
			return;
		email=newEmail;
	}


	@Override
	public int getId() {
		return id;
	}
	
	void clearState() {
		userCounter=0;
	}
	public static void setCounter(int updatedCount) {
		userCounter = updatedCount;
	}
}
