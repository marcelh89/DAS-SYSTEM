package de.fhb.dassystem.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;

import de.fhb.dassystem.db.entity.User;
import de.fhb.dassystem.login.HibernateUtil;

public class UserDAO {
	protected static Logger logger = Logger.getLogger(UserDAO.class
			.getName());

	private Session session;

	// Constructors

	public UserDAO() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	// Methods

	public void create(User user) {
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	public void update(User user) {
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
	}

	public void delete(User user) {
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
	}

	public User findById(int id) {
		session.beginTransaction();
		Query q = session.createQuery("from User where id = :id");
		q.setParameter("id", id);
		User user = (User) q.uniqueResult();
		session.getTransaction().commit();
		return user;
	}

	public User findByEmail(String email) {
		session.beginTransaction();
		Query q = session.createQuery("from User where Email = :email");
		q.setParameter("email", email);
		User user = (User) q.uniqueResult();
		session.getTransaction().commit();
		return user;
	}

	public List<User> findAll() {
		session.beginTransaction();
		Query q = session.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> users = (ArrayList<User>) q.list();
		session.getTransaction().commit();
		return users;
	}
}
