package de.fhb.dassystem.db.entity;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -282126619116500134L;
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	private Integer uid;
	@Column(name="email")
	private String email;
	@Column(name="forename")
	private String forename;
	@Column(name="surname")
	private String surname;
	@Column(name="password")
	private String password;
	@Column(name="birthdate")
	private Date birthDate;
	@Column(name="dozent")
	private boolean dozent;
	@Column(name="lastlocation")
	private String lastLocation;
	

	public User() {

	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isDozent() {
		return dozent;
	}

	public void setDozent(boolean dozent) {
		this.dozent = dozent;
	}

	public String getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(String lastLocation) {
		this.lastLocation = lastLocation;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", email=" + email + ", forename="
				+ forename + ", surname=" + surname + ", password=" + password
				+ ", birthDate=" + birthDate + ", dozent=" + dozent
				+ ", lastLocation=" + lastLocation + "]";
	}
	
}
