package de.fhb.dassystem.login;


public class UserDaoTester {

	public static void main(String args[]){
		UserDAO ud = new UserDAO();
		
		//findall
		System.out.println(ud.getall().toString());
		for (User user : ud.getall()) {
			System.out.println(user.getEmail()+ "  "+user.getPassword());
		}
		
		//findbyid
		User user2 = ud.findByID(1);
		System.out.println(user2.getEmail());
		
		//findbyEmail
		User user3 = ud.findByEmail("marcelh89@googlemail.com").get(0);
		System.out.println(user3.getEmail());
		
		
	}

}
