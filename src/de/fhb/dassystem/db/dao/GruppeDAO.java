package de.fhb.dassystem.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;

import de.fhb.dassystem.db.entity.Gruppe;

public class GruppeDAO {
	protected static Logger logger = Logger
			.getLogger(GruppeDAO.class.getName());

	private Session session;

	// Constructors

	public GruppeDAO(Session session) {
		this.session = session;
	}

	// Methods

	public void create(Gruppe gruppe) {
		session.beginTransaction();
		session.save(gruppe);
		session.getTransaction().commit();
	}

	public void update(Gruppe gruppe) {
		session.beginTransaction();
		session.update(gruppe);
		session.getTransaction().commit();
	}

	public void delete(Gruppe gruppe) {
		session.beginTransaction();
		session.delete(gruppe);
		session.getTransaction().commit();
	}

	public Gruppe findById(int id) {
		session.beginTransaction();
		Query q = session.createQuery("from Gruppe where id = :id");
		q.setParameter("id", id);
		Gruppe gruppe = (Gruppe) q.uniqueResult();
		session.getTransaction().commit();
		return gruppe;
	}

	public Gruppe findByName(String name) {
		session.beginTransaction();
		Query q = session.createQuery("from Gruppe where Name = :name");
		q.setParameter("name", name);
		Gruppe gruppe = (Gruppe) q.uniqueResult();
		session.getTransaction().commit();
		return gruppe;
	}

	public List<Gruppe> findAll() {
		session.beginTransaction();
		Query q = session.createQuery("from Gruppe");
		@SuppressWarnings("unchecked")
		List<Gruppe> gruppen = (ArrayList<Gruppe>) q.list();
		session.getTransaction().commit();
		return gruppen;
	}
}
