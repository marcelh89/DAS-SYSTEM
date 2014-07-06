package de.fhb.dassystem.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;

import de.fhb.dassystem.db.entity.User;
import de.fhb.dassystem.db.entity.VorlesungTeilnehmer;

public class VorlesungTeilnehmerDAO {
	protected static Logger logger = Logger.getLogger(VorlesungTeilnehmerDAO.class
			.getName());

	private Session session;

	// Constructors

	public VorlesungTeilnehmerDAO(Session session) {
//		session = HibernateUtil.getSessionFactory().openSession();
		this.session = session;
	}

	// Methods

	public void create(VorlesungTeilnehmer vorlesungTeilnehmer) {
		session.beginTransaction();
		session.save(vorlesungTeilnehmer);
		session.getTransaction().commit();
	}

	public void update(VorlesungTeilnehmer vorlesungTeilnehmer) {
		session.beginTransaction();
		session.update(vorlesungTeilnehmer);
		session.getTransaction().commit();
	}

	public void delete(VorlesungTeilnehmer vorlesungTeilnehmer) {
		session.beginTransaction();
		session.delete(vorlesungTeilnehmer);
		session.getTransaction().commit();
	}

	public VorlesungTeilnehmer findByDate(Date date) {
		session.beginTransaction();
		Query q = session.createQuery("from VorlesungTeilnehmer where id = :id");
		q.setParameter("id", date);
		VorlesungTeilnehmer vorlesungTeilnehmer = (VorlesungTeilnehmer) q.uniqueResult();
		session.getTransaction().commit();
		return vorlesungTeilnehmer;
	}

	public List<VorlesungTeilnehmer> findAll() {
		session.beginTransaction();
		Query q = session.createQuery("from VorlesungTeilnehmer");
		@SuppressWarnings("unchecked")
		List<VorlesungTeilnehmer> vorlesungTeilnehmers = (ArrayList<VorlesungTeilnehmer>) q.list();
		session.getTransaction().commit();
		return vorlesungTeilnehmers;
	}
	
	public List<User> findTeilnehmerByDateAndVorlesungId(Date date, int vid){
		session.beginTransaction();
		Query q = session.createQuery("from VorlesungTeilnehmer where datum = :date and vid = :vid");
		q.setParameter("date", date);
		q.setParameter("vid", vid);
		@SuppressWarnings("unchecked")
		List<VorlesungTeilnehmer> vorlesungTeilnehmer = (ArrayList<VorlesungTeilnehmer>) q.list();
		session.getTransaction().commit();
		List<User> teilnehmer = new ArrayList<>();
		for(VorlesungTeilnehmer vt : vorlesungTeilnehmer){
			teilnehmer.add(vt.getUser());
		}
		return teilnehmer;
	}

}
