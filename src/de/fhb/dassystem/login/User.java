package de.fhb.dassystem.login;

public class User {

	private String forename, surname, email, password;

	// registration
	public User(String forename, String surname, String email, String password) {
		this.email = email;
		this.password = password;
		this.forename = forename;
		this.surname = surname;
	}

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

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
