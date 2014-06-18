package de.fhb.dassystem.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import de.fhb.dassystem.db.entity.User;

public class JsonLoginTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static Logger logger = Logger.getLogger(JsonLoginTestServlet.class
			.getName());

	public JsonLoginTestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("email", "Timmeeehhh@Southpark.com");
		json.put("password", "qwertz");
		out.print(json);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// handle login

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		logger.info(email + " " + password);

		JSONObject user = new JSONObject();
		user.put("email", email);
		user.put("password", password);

		logger.info(user.toString());

		String url = "http://localhost:8080/DAS-SYSTEM-SERVER/dassystem/login";

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);

			// add header
			// post.setHeader("User-Agent", USER_AGENT);
			post.setHeader("Content-type", "application/json");

			post.setEntity(new StringEntity(user.toString()));

			HttpResponse resp = httpclient.execute(post);

			int statuscode = resp.getStatusLine().getStatusCode();

			if (statuscode == 200) {
				System.out.println("BENUTZER AKZEPTIERT");
			} else {
				System.out.println("BENUTZER NICHT VORHANDEN");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
