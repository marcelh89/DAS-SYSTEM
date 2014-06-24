package de.fhb.dassystem.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.hibernate.Session;


import de.fhb.dassystem.db.dao.UserDAO_JDBC;
import de.fhb.dassystem.db.dao.VorlesungWochentagDAO;
import de.fhb.dassystem.db.entity.User_old;
import de.fhb.dassystem.db.entity.VorlesungWochentag;
import de.fhb.dassystem.login.HibernateUtil;

public class DasSystemRESTAccessor implements IDasSystemRESTAccessor{
	
	private final static Logger LOGGER = Logger.getLogger(DasSystemRESTAccessor.class.getName()); 
	private UserDAO_JDBC ud = new UserDAO_JDBC();
	private static Session hibSession = HibernateUtil.getSessionFactory().openSession();
	
	@GET
	@Path("/hi")
	public User_old halloWelt(){
		return new User_old("hallo", "du", "da", "pilz", new Date(), false);
	}
	
	@Override
	@POST
	@Path("/login")
	public User_old login(User_old user) {
		System.out.println("RESTAccessor.login| email:"+user.getEmail()+" passwort:"+user.getPassword());
		List<User_old> users = ud.findByEmail(user.getEmail());
		for(User_old u : users){
			if(u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())){
				return u;
			}
		}
		return null;
	}

	@Override
	@POST
	@Path("/register")
	public boolean register(User_old user) {
		List<User_old> listUser = ud.getall();
		for(User_old u: listUser){
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

	@Override
	@GET
	@Path("/vorlesung/all")
	public List<VorlesungWochentag> getVorlesung() {
		VorlesungWochentagDAO dao = new VorlesungWochentagDAO(hibSession);
		return dao.findAll();
	}
}
