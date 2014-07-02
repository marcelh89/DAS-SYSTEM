package de.fhb.dassystem.login;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import de.fhb.dassystem.db.dao.UserDAO;
import de.fhb.dassystem.db.dao.VorlesungWochentagDAO;
import de.fhb.dassystem.db.entity.User;
import de.fhb.dassystem.db.entity.VorlesungWochentag;
import de.fhb.dassystem.valueobject.raum.Wochentag;


public class UserDaoTester {
	
	public static void main(String args[]){
		SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
		//TODO Testzwecke
		Calendar monTest = new GregorianCalendar(2014,5,30,9,15);
		System.out.println(sf.format(monTest.getTime()));
//		Calendar day = new GregorianCalendar();
//		day.setTime(new Date());
//		System.out.println(Wochentag.values()[day.get(Calendar.DAY_OF_WEEK)-1].toString());
//		System.out.println(day.get(Calendar.DAY_OF_WEEK));
//		
//		Calendar monTest = new GregorianCalendar(2014,5,30,9,15);
//		System.out.println(Wochentag.values()[monTest.get(Calendar.DAY_OF_WEEK)-1].toString());
//		
//		Calendar time = new GregorianCalendar();
//		Calendar time2 = new GregorianCalendar();
//		time.set(Calendar.HOUR_OF_DAY, day.get(Calendar.HOUR_OF_DAY));
//		time.set(Calendar.MINUTE, day.get(Calendar.MINUTE));
//		time2.set(Calendar.HOUR_OF_DAY, 14);
//		time2.set(Calendar.MINUTE, 55);
//		
//		System.out.println(time.after(time2));
		
//		VorlesungWochentagDAO dao = new VorlesungWochentagDAO(HibernateUtil.getSessionFactory().openSession());
//		for(VorlesungWochentag vwo : dao.findAll()){
//			System.out.println(vwo);
//		}
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  
//        Session session = sessionFactory.openSession();  
//		UserDAO dao = new UserDAO();
//		User u = dao.findByEmail("bla@bla.de");
//		System.out.println(u);
//        session.beginTransaction();  
//    	Query q = session.createQuery("from User where email = :email");
//    	q.setParameter("email", "bla@bla.de");
//    	User user = (User) q.uniqueResult();
//    	session.getTransaction().commit();
//        
//    	System.out.println(user);
//          
//        session.close();  
//		UserDAO ud = new UserDAO();
		
//		User newUser = new User("bla","bla","bla@bla.de","bla",new Date(), false);
//		ud.create(newUser);
		//findall
//		System.out.println(ud.getall().toString());
//		for (User user : ud.getall()) {
//			System.out.println(user.getEmail()+ "  "+user.getPassword());
//			System.out.println(user);
//		}
//		System.out.println(ud.findByEmail("bla@bla.de"));
		
		//findbyid
//		User user2 = ud.findByID(1);
//		System.out.println(user2.getEmail());
		
		//findbyEmail
//		User user3 = ud.findByEmail("marcelh89@googlemail.com").get(0);
//		System.out.println(user3.getEmail());
		
		
		
		
	}

}
