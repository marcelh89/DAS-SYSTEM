package de.fhb.dassystem.login;

import java.util.Date;

import de.fhb.dassystem.db.dao.UserDAO;
import de.fhb.dassystem.db.entity.User;


public class UserDaoTester {

	public static void main(String args[]){
		UserDAO ud = new UserDAO();
		
//		User newUser = new User("bla","bla","bla@bla.de","bla",new Date(), false);
//		ud.create(newUser);
		//findall
//		System.out.println(ud.getall().toString());
//		for (User user : ud.getall()) {
//			System.out.println(user.getEmail()+ "  "+user.getPassword());
//			System.out.println(user);
//		}
		System.out.println(ud.findByEmail("bla@bla.de"));
		
		//findbyid
//		User user2 = ud.findByID(1);
//		System.out.println(user2.getEmail());
		
		//findbyEmail
//		User user3 = ud.findByEmail("marcelh89@googlemail.com").get(0);
//		System.out.println(user3.getEmail());
		
		
		
		
	}

}
