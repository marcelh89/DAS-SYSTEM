package de.fhb.dassystem.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.fhb.dassystem.db.entity.User;
import de.fhb.dassystem.db.entity.User_old;
import de.fhb.dassystem.db.entity.VorlesungWochentag;
import de.fhb.dassystem.valueobject.raum.RauminfoIn;
import de.fhb.dassystem.valueobject.raum.Rauminformation;

@Path("/dassystem")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IDasSystemRESTAccessor {
	
	@GET
	@Path("/hi")
	public User_old halloWelt();
	
	@GET
	@Path("/testuser")
	public List<User> getUser();
	
	@POST
	@Path("/login")
	public User_old login(User_old user);
	@POST
	@Path("/login2")
	public User login2(User user);
	@POST
	@Path("/register")
	public boolean register(User user);
	
	@GET
	@Path("/vorlesung/all")
	public List<VorlesungWochentag> getVorlesung();
	
	@POST
	@Path("/rauminfo/")
	public Rauminformation getRauminformation(RauminfoIn rIn);
	@GET
	@Path("/rauminfo/test")
	public Rauminformation getRauminformation();
//	public List<VorlesungWochentag> getVorlesung();
}
