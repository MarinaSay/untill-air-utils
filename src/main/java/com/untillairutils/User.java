package com.untillairutils;

public class User {

	public String displayName;
	public String firstName;
	public String lastName;
	public String language;
	public String posPassword;

	public User(String displayName, String firstName, String lastName, String language, String posPassword) {
		this.displayName = displayName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.language = language;
		this.posPassword = posPassword;

	}

	public static User[] inputUsers() {
		User[] users = new User[3];
		users[0] = new User("Magda", "Magda", "Magda", "Dutch", "1");
		users[1] = new User("Frank", "Frank", "Frank", "English", "2");
		users[2] = new User("Elena", "Elena", "Elena", "French", "4");
		return users;
	}

}
