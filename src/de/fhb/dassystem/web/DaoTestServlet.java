package de.fhb.dassystem.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import de.fhb.dassystem.db.dao.GruppeDAO;
import de.fhb.dassystem.db.dao.UserDAO;
import de.fhb.dassystem.db.entity.Gruppe;
import de.fhb.dassystem.db.entity.User;
import de.fhb.dassystem.login.HibernateUtil;

/**
 * Servlet implementation class DaoTestServlet
 */
@WebServlet("/DaoTestServlet")
public class DaoTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Session session = HibernateUtil.getSessionFactory().openSession();

	UserDAO uDAO = new UserDAO(session);
	GruppeDAO gDAO = new GruppeDAO(session);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DaoTestServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		List<User> users = uDAO.findAll();
		User systemUser = uDAO.findById(2);
		User marcelUser = uDAO.findById(1);

		out.println("<html>");
		out.println("<head>");
		out.println("<title>UpdateTest</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<ul>");

		for (User user : users) {
			out.println("<li>" + user.toString() + "</li>");
		}

		// update Group
		List<Gruppe> groups = gDAO.findAll();
		Gruppe group = groups.get(groups.size() - 1);

		out.println("<h1>" + "ALTE GRUPPE" + "</h1>");
		out.println("<p>" + group.toString() + "</p>");
		out.println("<p>" + group.getUsers().toString() + "</p>");

		// update users
		group.setUsers(users);

		gDAO.update(group);

		out.println("<h1>" + "NEUE GRUPPE" + "</h1>");
		out.println("<p>" + group.toString() + "</p>");
		out.println("<p>" + group.getUsers().toString() + "</p>");

		out.println("</ul>");
		out.println("</body>");
		out.println("</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
