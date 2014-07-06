package de.fhb.dassystem.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.hibernate.Session;

import de.fhb.dassystem.db.dao.GruppeDAO;
import de.fhb.dassystem.db.dao.UserDAO;
import de.fhb.dassystem.db.dao.UserDAO_JDBC;
import de.fhb.dassystem.db.dao.VorlesungDAO;
import de.fhb.dassystem.db.dao.VorlesungTeilnehmerDAO;
import de.fhb.dassystem.db.dao.VorlesungWochentagDAO;
import de.fhb.dassystem.db.entity.Gruppe;
import de.fhb.dassystem.db.entity.User;
import de.fhb.dassystem.db.entity.User_old;
import de.fhb.dassystem.db.entity.Vorlesung;
import de.fhb.dassystem.db.entity.VorlesungTeilnehmer;
import de.fhb.dassystem.db.entity.VorlesungWochentag;
import de.fhb.dassystem.login.HibernateUtil;
import de.fhb.dassystem.valueobject.gruppe.FreundEinladenIn;
import de.fhb.dassystem.valueobject.kurs.KursAnmeldenIn;
import de.fhb.dassystem.valueobject.raum.RauminfoIn;
import de.fhb.dassystem.valueobject.raum.Rauminformation;
import de.fhb.dassystem.valueobject.raum.TeilnehmerIn;

public class DasSystemRESTAccessor implements IDasSystemRESTAccessor {

	private final static Logger LOGGER = Logger
			.getLogger(DasSystemRESTAccessor.class.getName());
	private UserDAO_JDBC ud = new UserDAO_JDBC();
	private UserDAO uDao;
	private GruppeDAO gDao;
	private VorlesungDAO vDao;
	private VorlesungWochentagDAO vwDao;
	private VorlesungTeilnehmerDAO vtDao;
	private static Session hibSession = HibernateUtil.getSessionFactory()
			.openSession();

	private static final int SYSTEM_ID = 2;

	public DasSystemRESTAccessor() {
		uDao = new UserDAO(hibSession);
		gDao = new GruppeDAO(hibSession);
		vDao = new VorlesungDAO(hibSession);
		vwDao = new VorlesungWochentagDAO(hibSession);
		vtDao = new VorlesungTeilnehmerDAO(hibSession);
	}

	@GET
	@Path("/hi")
	public User_old halloWelt() {
		return new User_old("hallo", "du", "da", "pilz", new Date(), false);
	}

	@Override
	@POST
	@Path("/login")
	public User_old login(User_old user) {
		System.out.println("RESTAccessor.login| email:" + user.getEmail()
				+ " passwort:" + user.getPassword());
		List<User_old> users = ud.findByEmail(user.getEmail());
		for (User_old u : users) {
			if (u.getEmail().equals(user.getEmail())
					&& u.getPassword().equals(user.getPassword())) {
				return u;
			}
		}
		return null;
	}

	@Override
	@POST
	@Path("/login2")
	public User login2(User user) {
		System.out.println("RESTAccessor.login| email:" + user.getEmail()
				+ " passwort:" + user.getPassword());
		// uDao = new UserDAO(hibSession);
		User u = uDao.findByEmail(user.getEmail());
		System.out.println(u);
		if(u!=null){
			if (u.getPassword().equals(user.getPassword())) {
				return u;
			}	
		}
		
		return null;
	}

	@Override
	@POST
	@Path("/register")
	public boolean register(User user) {
		boolean retVal = false;
		if (uDao.findByEmail(user.getEmail()) == null) {
			uDao.create(user);
			retVal = true;
		} else {
			retVal = false;
		}
		return retVal;
	}

	@Override
	@GET
	@Path("/vorlesung/all")
	public List<VorlesungWochentag> getVorlesung() {
		// vwDao = new VorlesungWochentagDAO(hibSession);
		return vwDao.findAll();
	}

	@Override
	@POST
	@Path("/rauminfo/")
	public Rauminformation getRauminformation(RauminfoIn rIn) {
		SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
		Rauminformation raumInfo = null;
		try {
			VorlesungWochentag vorlesungWochentag = vwDao.findByDateAndRaumNr(
					sf.parse(rIn.getDatum()), rIn.getRaumNr());
			if (vorlesungWochentag != null) {
				raumInfo = new Rauminformation();
				raumInfo.setBegin(vorlesungWochentag.getBegin());
				raumInfo.setEnde(vorlesungWochentag.getEnde());
				raumInfo.setRaumNr(vorlesungWochentag.getRaumnr());
				// raumInfo.setVorlesung(vorlesungWochentag.getVorlesung());
				raumInfo.setVid(vorlesungWochentag.getVorlesung().getVid());
				raumInfo.setInhalt(vorlesungWochentag.getVorlesung()
						.getInhalt());
				raumInfo.setName(vorlesungWochentag.getVorlesung().getName());
				raumInfo.setAnmeldecode(vorlesungWochentag.getVorlesung()
						.getAnmeldecode());
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
		Calendar monTest = new GregorianCalendar(2014, 5, 30, 9, 15);
		Rauminformation raumInfo = null;
		VorlesungWochentag vorlesungWochentag = vwDao.findByDateAndRaumNr(
				monTest.getTime(), "A34");
		if (vorlesungWochentag != null) {
			raumInfo = new Rauminformation();
			raumInfo.setBegin(vorlesungWochentag.getBegin());
			raumInfo.setEnde(vorlesungWochentag.getEnde());
			raumInfo.setRaumNr(vorlesungWochentag.getRaumnr());
			// raumInfo.setVorlesung(vorlesungWochentag.getVorlesung());
			raumInfo.setVid(vorlesungWochentag.getVorlesung().getVid());
			raumInfo.setInhalt(vorlesungWochentag.getVorlesung().getInhalt());
			raumInfo.setName(vorlesungWochentag.getVorlesung().getName());
			raumInfo.setAnmeldecode(vorlesungWochentag.getVorlesung()
					.getAnmeldecode());
		}

		return raumInfo;
	}

	@Override
	@GET
	@Path("/testuser")
	public List<User> getUser() {
		List<User> users =uDao.findAll();
		for(User u : users){
			System.out.println(u);
		}
		return users;
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
			VorlesungWochentag vorWoch = vwDao.findByVorlesungIdAndDate(vorlesung.getVid(), sf.parse(kIn.getDatum()));
			VorlesungTeilnehmer vt = new VorlesungTeilnehmer();
			vt.setDatum(sf.parse(kIn.getDatum()));
			vt.setVorlesung(vorlesung);
			vt.setUser(user);
			rauminfo = new Rauminformation();
			rauminfo.setName(vorlesung.getName());
			rauminfo.setInhalt(vorlesung.getInhalt());
			rauminfo.setRaumNr(vorWoch.getRaumnr());
			System.out.println(rauminfo);
			vtDao.create(vt);
		} catch (ParseException e) {
			e.printStackTrace();
			rauminfo = null;
		}
		return rauminfo;
	}

	@Override
	public List<Gruppe> getGroups() {
		return gDao.findAll();
	}

	@Override
	public List<Gruppe> getGroups(User user) {

		List<Gruppe> grouplist = gDao.findAll();
		List<Gruppe> returnlist = new ArrayList<>();

		// add all system groups
		for (Gruppe gruppe : grouplist) {
			List<User> users = gruppe.getUsers();

			for (User u : users) {
				if (u.getUid().equals(SYSTEM_ID))
					returnlist.add(gruppe);
			}

		}

		// add user specific groups
		for (Gruppe gruppe : grouplist) {
			List<User> users = gruppe.getUsers();

			for (User u : users) {
				if (u.getUid().equals(user.getUid()))
					returnlist.add(gruppe);
			}

		}

		return returnlist;
	}

	@Override
	public boolean addGroup(Gruppe gruppe) {
		boolean success = false;

		// check if group already in db
		Gruppe dbGroup = gDao.findById(gruppe.getGid());

		if (dbGroup != null) {
			success = false;
		} else {

			success = true;

			// only for test purpose
			// gruppe.setUsers(null);

			User dbCreator = uDao.findById(gruppe.getCreator().getUid());
			gruppe.setCreator(dbCreator);

			gDao.create(gruppe);

		}

		return success;
	}

	@Override
	public boolean deleteGroup(Gruppe gruppe) {
		boolean success = false;

		// check if group already in db
		Gruppe dbGroup = gDao.findById(gruppe.getGid());

		if (dbGroup == null) {
			success = false;
		} else {

			success = true;

			gDao.delete(dbGroup);

		}

		return success;

	}

	@Override
	public boolean updateGroup(FreundEinladenIn freundEinladenIn) {
		System.out.println(freundEinladenIn);
		boolean success = false;

		// check if goup exist in db
		Gruppe dbGroup = gDao.findById(freundEinladenIn.getGruppenid());

		// no
		if (dbGroup == null) {
			success = false;

		} else { // yes

			// find newUser in DB
			User dbUser = uDao.findById(freundEinladenIn.getUserid());

			if (dbUser == null) {
				success = false;
			} else {

				List<User> users = dbGroup.getUsers();
				users.add(dbUser);
				dbGroup.setUsers(users);
				gDao.update(dbGroup);

				success = true;
			}

		}

		return success;
	}

	@Override
	@GET
	@Path("/vorlesung/{dozentid}")
	public List<Vorlesung> getVorlesungByDozent(
			@PathParam("dozentid") int dozentid) {
		return vDao.findAllByDozentId(dozentid);
	}

	@Override
	@POST
	@Path("/vorlesung/update")
	public Boolean updateVorlesungCode(Vorlesung vorlesung) {
		boolean retVal = false;
		Vorlesung vDb = null;
		try {
			vDb = vDao.findById(vorlesung.getVid());
			vDb.setAnmeldecode(vorlesung.getAnmeldecode());
			vDao.update(vDb);
			retVal = true;
		} catch (Exception e) {
			e.printStackTrace();
			retVal = false;
		}
		return retVal;
	}

	@Override
	public List<User> getVorlesungTeilnehmer(TeilnehmerIn tin) {
		SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
		List<User> teilnehmer = new ArrayList<User>();
		System.out.println(tin.getDatum() + " " + tin.getVorlesungId());
		try {
			teilnehmer = vtDao.findTeilnehmerByDateAndVorlesungId(
					sf.parse(tin.getDatum()), tin.getVorlesungId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teilnehmer;
	}

	@Override
	public List<User> getVorlesungTeilnehmerTest() {
		System.out.println("IN Methode");
		SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
		List<User> teilnehmer = new ArrayList<User>();
		try {
			teilnehmer = vtDao.findTeilnehmerByDateAndVorlesungId(
					sf.parse("05.07.2014"), 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teilnehmer;
	}

	@Override
	@PUT
	public void updateLastLocationUser(User user) {
		System.out.println(user);
		User dbUser = uDao.findById(user.getUid());
		dbUser.setLastLocation(user.getLastLocation());
		uDao.update(dbUser);
	}

}
