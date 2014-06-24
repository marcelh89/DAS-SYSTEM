package de.fhb.dassystem.login;

import de.fhb.dassystem.db.dao.UserDAO;
import de.fhb.dassystem.db.dao.VorlesungWochentagDAO;
import de.fhb.dassystem.db.entity.User;
import de.fhb.dassystem.db.entity.VorlesungWochentag;


public class UserDaoTester {
	
	public static void main(String args[]){
		VorlesungWochentagDAO dao = new VorlesungWochentagDAO(HibernateUtil.getSessionFactory().openSession());
		for(VorlesungWochentag vwo : dao.findAll()){
			System.out.println(vwo);
		}
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
