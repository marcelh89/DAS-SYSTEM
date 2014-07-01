package de.fhb.dassystem.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;

import de.fhb.dassystem.db.entity.User;
import de.fhb.dassystem.db.entity.VorlesungWochentag;

public class VorlesungWochentagDAO {
	protected static Logger logger = Logger.getLogger(VorlesungWochentagDAO.class
			.getName());

	private Session session;

	// Constructors

	public VorlesungWochentagDAO(Session session) {
//		session = HibernateUtil.getSessionFactory().openSession();
		this.session = session;
	}

	// Methods

	public void create(VorlesungWochentag vorlesungWochentag) {
		session.beginTransaction();
		session.save(vorlesungWochentag);
		session.getTransaction().commit();
	}

	public void update(VorlesungWochentag vorlesungWochentag) {
		session.beginTransaction();
		session.update(vorlesungWochentag);
		session.getTransaction().commit();
	}

	public void delete(VorlesungWochentag vorlesungWochentag) {
		session.beginTransaction();
		session.delete(vorlesungWochentag);
		session.getTransaction().commit();
	}

	public User findById(int id) {
		session.beginTransaction();
		Query q = session.createQuery("from VorlesungWochentag where id = :id");
		q.setParameter("id", id);
		User user = (User) q.uniqueResult();
		session.getTransaction().commit();
		return user;
	}

	public List<VorlesungWochentag> findAll() {
		session.beginTransaction();
		Query q = session.createQuery("from VorlesungWochentag");
		@SuppressWarnings("unchecked")
		List<VorlesungWochentag> vorlesungWochentage = (ArrayList<VorlesungWochentag>) q.list();
		session.getTransaction().commit();
		return vorlesungWochentage;
	}
}
