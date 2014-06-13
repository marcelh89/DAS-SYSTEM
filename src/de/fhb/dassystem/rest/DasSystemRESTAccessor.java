package de.fhb.dassystem.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


import de.fhb.dassystem.db.dao.UserDAO;
import de.fhb.dassystem.db.entity.User;

public class DasSystemRESTAccessor implements IDasSystemRESTAccessor{
	
	private final static Logger LOGGER = Logger.getLogger(DasSystemRESTAccessor.class.getName()); 
	private UserDAO ud = new UserDAO();
	
	@GET
	@Path("/hi")
	public User halloWelt(){
		return new User("hallo", "du", "da", "pilz", new Date(), false);
	}
	
	@Override
	@POST
	@Path("/login")
	public User login(User user) {
		System.out.println("RESTAccessor.login| email:"+user.getEmail()+" passwort:"+user.getPassword());
		List<User> users = ud.findByEmail(user.getEmail());
		for(User u : users){
			if(u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())){
				return u;
			}
		}
		return null;
	}

	@Override
	@POST
	@Path("/register")
	public boolean register(User user) {
		List<User> listUser = ud.getall();
		for(User u: listUser){
			if(u.getEmail().equals(user.getEmail())){
				return false;
			}
		}
//		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
//		Date birthDate;
//		try {
//			birthDate = sf.parse(userbirthdate); 
//		} catch (ParseException e) {
//			LOGGER.warning("Konnte Geburtsdatum nicht setzen: "+birthdate);
////			e.printStackTrace();
//			birthDate = null;
//		}
		ud.create(user);
		return true;
	}
}
