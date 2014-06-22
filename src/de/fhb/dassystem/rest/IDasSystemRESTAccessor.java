package de.fhb.dassystem.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.fhb.dassystem.db.entity.User;

@Path("/dassystem")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IDasSystemRESTAccessor {
	
	@GET
	@Path("/hi")
	@Produces(MediaType.APPLICATION_JSON)
	public User halloWelt();
	@POST
	@Path("/login")
	public User login(User user);
	@POST
	@Path("/register")
	public boolean register(User user);
}