package de.fhb.dassystem.login;

import java.util.Date;

public class User {

	private String forename, surname, email, password, username;
	private Date birthdate;

	// Registrierung
	public User(String forename, String surname, String email, Date birthdate) {
		super();
		this.forename = forename;
		this.surname = surname;
		this.birthdate = birthdate;
	}

	// Login
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User() {

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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
