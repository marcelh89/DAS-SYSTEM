package de.fhb.dassystem.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import de.fhb.dassystem.db.dao.UserDAO_JDBC;
import de.fhb.dassystem.db.entity.User_old;

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
		
		System.out.println(email);
		System.out.println(password);

		UserDAO_JDBC userdao = new UserDAO_JDBC();
		List<User_old> users = userdao.findByEmail(email);

		if (!users.isEmpty()) {
			User_old user = users.get(0);
			System.out.println("eins");
			if (user.getPassword().equals(password)) {
				System.out.println("zwei");

				// set Cookie
				servletResponse
						.addCookie(new Cookie("ACCESS", "AUTHENTICATED"));
				servletResponse.addCookie(new Cookie("EMAIL", email));
				servletResponse
						.sendRedirect("http://localhost:8080/DAS-SYSTEM-SERVER/success.jsp");

			}

		} else {
			System.out.println("fail");
			servletResponse.addCookie(new Cookie("ACCESS", "REJECTED"));
			servletResponse
					.sendRedirect("http://localhost:8080/DAS-SYSTEM-SERVER/login.jsp");
		}
	}

//	@POST
//	@Path("/registration")
//	@Produces("application/json")
//	public void registration(@Context HttpServletResponse servletResponse,
//			@FormParam("forename") String forename,
//			@FormParam("surname") String surname,
//			@FormParam("email") String email,
//			@FormParam("passwd") String password,
//			@FormParam("passwd_wdh") String passwordWdh) throws IOException {
//
//		UserDAO userdao = new UserDAO();
//		List<User> users = userdao.findByEmail(email);
//
//		boolean passwordEqual = password.equals(passwordWdh);
//		boolean userExists = !users.isEmpty();
//		
//		if (!passwordEqual || userExists) {
//			servletResponse
//					.sendRedirect("http://localhost:8080/DAS-SYSTEM-SERVER/registration.jsp");
//		} else {
//
//			//create new User in DB
//			User user = new User(forename, surname, email, password);
//			 
//			userdao.create(user);
//			
//			servletResponse
//					.sendRedirect("http://localhost:8080/DAS-SYSTEM-SERVER/login.jsp");
//
//		}
//	}

}
