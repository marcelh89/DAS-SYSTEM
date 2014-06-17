//This sample is how to use websocket of Tomcat7.0.52.
//web.xml is not required. Because you can use Servlet3.0 Annotation.
package de.fhb.dassystem.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import de.fhb.dassystem.db.entity.User;

@ServerEndpoint(value = "/wschat")
public class WsChat {
	private static ArrayList<Session> sessionList = new ArrayList<Session>();

	User user = new User("Peter", "Lustig", "123@123.de", "123", new Date(),
			false);

	@OnOpen
	public void onOpen(Session session) {
		try {
			sessionList.add(session);
			// asynchronous communication
			session.getBasicRemote().sendText("Hello!");

			// GET USER FROM SESSION SCOPE
			// session.getUserProperties().get("user", user);
			// session.getUserPrincipal());

		} catch (IOException e) {
		}
	}

	@OnClose
	public void onClose(Session session) {
		sessionList.remove(session);
	}

	@OnMessage
	public void onMessage(String msg) {
		try {
			for (Session session : sessionList) {
				// asynchronous communication
				session.getBasicRemote().sendText(
						user.getForename() + user.getSurname().charAt(0) + "> "
								+ msg);

			}
		} catch (IOException e) {
		}
	}
}