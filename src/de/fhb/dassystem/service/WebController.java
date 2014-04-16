package de.fhb.dassystem.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import de.fhb.dassystem.login.User;
import de.fhb.dassystem.login.UserDAO;

/**
 * @author Marcel Hinderlich
 * 
 */

@Path("/webservice")
public class WebController {

	@GET
	@Path("/hello")
	@Produces("text/plain")
	public String hello() {
		return "Hello World!!! dineshonjava";
	}

	@GET
	@Path("/message/{message}")
	@Produces("text/plain")
	public String showMsg(@PathParam("message") String message) {
		return message;
	}


	@POST
	@Path("/login")
	@Produces("application/json")
	public void login(@Context HttpServletResponse servletResponse,
			@FormParam("email") String email,
			@FormParam("passwd") String password) throws IOException {

		UserDAO userdao = new UserDAO();
		List<User> users = userdao.findByEmail(email);

		if (!users.isEmpty()) {
			System.out.println("first");
			User user = users.get(0);

			if (user.getPassword().equals(password)) {
				System.out.println("second");
				servletResponse
						.sendRedirect("http://localhost:8080/DAS-SYSTEM-SERVER/success.jsp");

				// set Cookie or login flag to session
			}

		} else {
			
			servletResponse
					.sendRedirect("http://localhost:8080/DAS-SYSTEM-SERVER/login.jsp");
		}
	}

}
