package de.fhb.dassystem.db.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Gruppe")
public class Gruppe implements Serializable {

	private static final long serialVersionUID = 8740865935045063839L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer gid;
	@Column(name = "name")
	private String name;
	@Column(name = "ispublic")
	private boolean isPublic;
//	@OneToOne
//	@NotNull
//	User creator; // one of the users
//	@OneToMany
//	@NotNull
//	List<User> users;

	public Gruppe(String name, boolean isPublic, User creator) {
		this.name = name;
		this.isPublic = isPublic;
//		this.creator = creator;
//		this.users = new ArrayList<User>();
//		users.add(creator);
	}

	public Gruppe() {
		this(null, false, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	@Override
	public String toString() {
		return name;
	}

//	public User getCreator() {
//		return creator;
//	}
//
//	public void setCreator(User creator) {
//		this.creator = creator;
//	}
//
//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}

}
