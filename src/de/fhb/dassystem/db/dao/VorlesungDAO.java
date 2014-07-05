package de.fhb.dassystem.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;

import de.fhb.dassystem.db.entity.Vorlesung;

public class VorlesungDAO {
	protected static Logger logger = Logger.getLogger(VorlesungDAO.class
			.getName());

	private Session session;

	// Constructors

	public VorlesungDAO(Session session) {
//		session = HibernateUtil.getSessionFactory().openSession();
		this.session = session;
	}

	// Methods

	public void create(Vorlesung vorlesung) {
		session.beginTransaction();
		session.save(vorlesung);
		session.getTransaction().commit();
	}

	public void update(Vorlesung vorlesung) {
		session.beginTransaction();
		session.update(vorlesung);
		session.getTransaction().commit();
	}

	public void delete(Vorlesung vorlesung) {
		session.beginTransaction();
		session.delete(vorlesung);
		session.getTransaction().commit();
	}

	public Vorlesung findById(int id) {
		session.beginTransaction();
		Query q = session.createQuery("from Vorlesung where id = :id");
		q.setParameter("id", id);
		Vorlesung vorlesung = (Vorlesung) q.uniqueResult();
		session.getTransaction().commit();
		return vorlesung;
	}

	public List<Vorlesung> findAll() {
		session.beginTransaction();
		Query q = session.createQuery("from Vorlesung");
		@SuppressWarnings("unchecked")
		List<Vorlesung> vorlesungen = (ArrayList<Vorlesung>) q.list();
		session.getTransaction().commit();
		return vorlesungen;
	}
	
	public Vorlesung findByAnmeldecode(String anmeldecode){
		session.beginTransaction();
		Query q = session.createQuery("from Vorlesung where anmeldecode = :anmeldecode");
		q.setParameter("anmeldecode", anmeldecode);
		Vorlesung vorlesung = (Vorlesung) q.uniqueResult();
		session.getTransaction().commit();
		return vorlesung;
	}
	
	public List<Vorlesung> findAllByDozentId(int dozentId){
		session.beginTransaction();
		Query q = session.createQuery("from Vorlesung where dozentid = :dozentId");
		q.setParameter("dozentId", dozentId);
		@SuppressWarnings("unchecked")
		List<Vorlesung> vorlesungen = (List<Vorlesung>) q.list();
		session.getTransaction().commit();
		return vorlesungen;
	}
	
}
