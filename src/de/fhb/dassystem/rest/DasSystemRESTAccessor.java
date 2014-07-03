package de.fhb.dassystem.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.hibernate.Session;


import de.fhb.dassystem.db.dao.UserDAO;
import de.fhb.dassystem.db.dao.UserDAO_JDBC;
import de.fhb.dassystem.db.dao.VorlesungDAO;
import de.fhb.dassystem.db.dao.VorlesungTeilnehmerDAO;
import de.fhb.dassystem.db.dao.VorlesungWochentagDAO;
import de.fhb.dassystem.db.entity.User;
import de.fhb.dassystem.db.entity.User_old;
import de.fhb.dassystem.db.entity.Vorlesung;
import de.fhb.dassystem.db.entity.VorlesungTeilnehmer;
import de.fhb.dassystem.db.entity.VorlesungWochentag;
import de.fhb.dassystem.login.HibernateUtil;
import de.fhb.dassystem.valueobject.kurs.KursAnmeldenIn;
import de.fhb.dassystem.valueobject.raum.RauminfoIn;
import de.fhb.dassystem.valueobject.raum.Rauminformation;

public class DasSystemRESTAccessor implements IDasSystemRESTAccessor{
	
	private final static Logger LOGGER = Logger.getLogger(DasSystemRESTAccessor.class.getName()); 
	private UserDAO_JDBC ud = new UserDAO_JDBC();
	private UserDAO uDao;
	private VorlesungDAO vDao;
	private VorlesungWochentagDAO vwDao;
	private VorlesungTeilnehmerDAO vtDao;
	private static Session hibSession = HibernateUtil.getSessionFactory().openSession();
	
	public DasSystemRESTAccessor(){
		uDao = new UserDAO(hibSession);
		vDao = new VorlesungDAO(hibSession);
		vwDao = new VorlesungWochentagDAO(hibSession);
		vtDao = new VorlesungTeilnehmerDAO(hibSession);
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
		System.out.println(u);
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

	@Override
	@POST
	@Path("/rauminfo/")
	public Rauminformation getRauminformation(RauminfoIn rIn) {
		SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
		Rauminformation raumInfo = null;	
		try {
			VorlesungWochentag vorlesungWochentag = vwDao.findByDateAndRaumNr(sf.parse(rIn.getDatum()), rIn.getRaumNr());
			if(vorlesungWochentag != null){
				raumInfo = new Rauminformation();
				raumInfo.setBegin(vorlesungWochentag.getBegin());
				raumInfo.setEnde(vorlesungWochentag.getEnde());
				raumInfo.setRaumNr(vorlesungWochentag.getRaumnr());
//				raumInfo.setVorlesung(vorlesungWochentag.getVorlesung());	
				raumInfo.setVid(vorlesungWochentag.getVorlesung().getVid());
				raumInfo.setInhalt(vorlesungWochentag.getVorlesung().getInhalt());
				raumInfo.setName(vorlesungWochentag.getVorlesung().getName());
				raumInfo.setAnmeldecode(vorlesungWochentag.getVorlesung().getAnmeldecode());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(raumInfo);
		return raumInfo;
	}

	@Override
	@GET
	@Path("/rauminfo/test")
	public Rauminformation getRauminformation() {
		Calendar monTest = new GregorianCalendar(2014,5,30,9,15);
		Rauminformation raumInfo = null;		
		VorlesungWochentag vorlesungWochentag = vwDao.findByDateAndRaumNr(monTest.getTime(), "A34");
		if(vorlesungWochentag != null){
			raumInfo = new Rauminformation();
			raumInfo.setBegin(vorlesungWochentag.getBegin());
			raumInfo.setEnde(vorlesungWochentag.getEnde());
			raumInfo.setRaumNr(vorlesungWochentag.getRaumnr());
//			raumInfo.setVorlesung(vorlesungWochentag.getVorlesung());	
			raumInfo.setVid(vorlesungWochentag.getVorlesung().getVid());
			raumInfo.setInhalt(vorlesungWochentag.getVorlesung().getInhalt());
			raumInfo.setName(vorlesungWochentag.getVorlesung().getName());
			raumInfo.setAnmeldecode(vorlesungWochentag.getVorlesung().getAnmeldecode());
		}
		
		return raumInfo;
	}

	@Override
	@GET
	@Path("/testuser")
	public List<User> getUser() {
		return uDao.findAll();
	}

	@Override
	@GET
	@Path("/vorlesung/teilnehmer/all")
	public List<VorlesungTeilnehmer> getVorlesungsTeilnehmer() {
		return vtDao.findAll();
	}

	@Override
	@POST
	@Path("/vorlesung/teilnehmer/anmelden")
	public Rauminformation anKursAnmelden(KursAnmeldenIn kIn) {
		SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
		Rauminformation rauminfo = null;
		try {
			Vorlesung vorlesung = vDao.findByAnmeldecode(kIn.getAnmeldecode());
			User user = uDao.findById(kIn.getUserid());
			VorlesungTeilnehmer vt = new VorlesungTeilnehmer();
			vt.setDatum(sf.parse(kIn.getDatum()));
			vt.setVorlesung(vorlesung);
			vt.setUser(user);
			rauminfo = new Rauminformation();
			rauminfo.setName(vorlesung.getName());
			rauminfo.setInhalt(vorlesung.getInhalt());
			System.out.println(rauminfo);
			vtDao.create(vt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rauminfo;
	}




}
