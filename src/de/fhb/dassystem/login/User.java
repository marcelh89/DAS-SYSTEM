package de.fhb.dassystem.login;

public class User {

	private String email, password;

	// Login
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User() {

	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
