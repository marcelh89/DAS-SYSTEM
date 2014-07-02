package de.fhb.dassystem.db.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;

import de.fhb.dassystem.db.entity.User;
import de.fhb.dassystem.db.entity.VorlesungWochentag;
import de.fhb.dassystem.valueobject.raum.Wochentag;

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

	public VorlesungWochentag findById(int id) {
		session.beginTransaction();
		Query q = session.createQuery("from VorlesungWochentag where id = :id");
		q.setParameter("id", id);
		VorlesungWochentag vorlesungWochentag = (VorlesungWochentag) q.uniqueResult();
		session.getTransaction().commit();
		return vorlesungWochentag;
	}

	public List<VorlesungWochentag> findAll() {
		session.beginTransaction();
		Query q = session.createQuery("from VorlesungWochentag");
		@SuppressWarnings("unchecked")
		List<VorlesungWochentag> vorlesungWochentage = (ArrayList<VorlesungWochentag>) q.list();
		session.getTransaction().commit();
		return vorlesungWochentage;
	}
	
	public VorlesungWochentag findByDateAndRaumNr(Date date, String raumNr){
		Calendar day = new GregorianCalendar();
		day.setTime(date);
		//Aktuelle Zeit
		Calendar time = new GregorianCalendar();
		time.set(Calendar.HOUR_OF_DAY, day.get(Calendar.HOUR_OF_DAY));
		time.set(Calendar.MINUTE, day.get(Calendar.MINUTE));
		
		String tag = Wochentag.values()[day.get(Calendar.DAY_OF_WEEK)-1].toString();
		
		session.beginTransaction();
		Query q = session.createQuery("from VorlesungWochentag where wochentag = :tag and raumnr = :raumNr");
		q.setParameter("tag", tag);
		q.setParameter("raumNr", raumNr);
		List<VorlesungWochentag> vorlesungWochentagList = (List<VorlesungWochentag>)q.list();
		session.getTransaction().commit();
		System.out.println(vorlesungWochentagList.size());
		VorlesungWochentag vorlesungWochentag = null;
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm");

		Calendar begin = new GregorianCalendar(),ende = new GregorianCalendar(),parsed = new GregorianCalendar();
		for(VorlesungWochentag vw : vorlesungWochentagList){
			try {
				parsed.setTime(sf.parse(vw.getBegin()));
				begin.set(Calendar.HOUR_OF_DAY, parsed.get(Calendar.HOUR_OF_DAY));
				begin.set(Calendar.MINUTE, parsed.get(Calendar.MINUTE));
			
				parsed.setTime(sf.parse(vw.getEnde()));
				ende.set(Calendar.HOUR_OF_DAY, parsed.get(Calendar.HOUR_OF_DAY));
				ende.set(Calendar.MINUTE, parsed.get(Calendar.MINUTE));
			
				if(time.after(begin) && time.before(ende)){
					vorlesungWochentag = vw;	
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return vorlesungWochentag;
	}
}
