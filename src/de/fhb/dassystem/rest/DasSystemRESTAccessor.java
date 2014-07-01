package de.fhb.dassystem.rest;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.hibernate.Session;


import de.fhb.dassystem.db.dao.UserDAO;
import de.fhb.dassystem.db.dao.UserDAO_JDBC;
import de.fhb.dassystem.db.dao.VorlesungWochentagDAO;
import de.fhb.dassystem.db.entity.User;
import de.fhb.dassystem.db.entity.User_old;
import de.fhb.dassystem.db.entity.VorlesungWochentag;
import de.fhb.dassystem.login.HibernateUtil;

public class DasSystemRESTAccessor implements IDasSystemRESTAccessor{
	
	private final static Logger LOGGER = Logger.getLogger(DasSystemRESTAccessor.class.getName()); 
	private UserDAO_JDBC ud = new UserDAO_JDBC();
	private UserDAO uDao;
	private VorlesungWochentagDAO vwDao;
	private static Session hibSession = HibernateUtil.getSessionFactory().openSession();
	
	public DasSystemRESTAccessor(){
		uDao = new UserDAO(hibSession);
		vwDao = new VorlesungWochentagDAO(hibSession);
	}
	
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
	@Path("/login2")
	public User login2(User user) {
		System.out.println("RESTAccessor.login| email:"+user.getEmail()+" passwort:"+user.getPassword());
//		uDao = new UserDAO(hibSession);
		User u = uDao.findByEmail(user.getEmail());
		if(u.getPassword().equals(user.getPassword())){
			return u;
		}
		return null;
	}
	
	@Override
	@POST
	@Path("/register")
	public boolean register(User user) {
		boolean retVal = false;
		if(uDao.findByEmail(user.getEmail()) == null){
			uDao.create(user);
			retVal = true;
		}else{
			retVal = false;
		}
		return retVal;
	}

	@Override
	@GET
	@Path("/vorlesung/all")
	public List<VorlesungWochentag> getVorlesung() {
//		vwDao = new VorlesungWochentagDAO(hibSession);
		return vwDao.findAll();
	}
}
