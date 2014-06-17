//This sample is how to use websocket of Tomcat7.0.52.
//web.xml is not required. Because you can use Servlet3.0 Annotation.
package de.fhb.dassystem.chat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class WsChat {

	private static Set<Session> conns = java.util.Collections
			.synchronizedSet(new HashSet<Session>());
	private static Map<Session, String> nickNames = new ConcurrentHashMap<Session, String>();

	private Session currentSession;

	public WsChat() {
		System.out.println("Constructed!");
	}

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("WebSocket opened: " + session.getId());
		conns.add(session);
		this.currentSession = session;
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println("Received: " + message);
		if (!nickNames.containsKey(currentSession)) {
			// No nickname has been assigned by now
			// the first message is the nickname
			// escape the " character first
			message = message.replace("\"", "\\\"");

			// broadcast all the nicknames to him
			for (String nick : nickNames.values())
				sendMessageToAll("{\"addUser\":\"" + nick + "\"}");

			// Register the nickname with the
			nickNames.put(currentSession, message);

			// broadcast him to everyone now
			String messageToSend = "{\"addUser\":\"" + message + "\"}";

			sendMessageToAll(messageToSend);

		} else {
			// Broadcast the message
			String messageToSend = "{\"nickname\":\""
					+ nickNames.get(currentSession) + "\", \"message\":\""
					+ message.replace("\"", "\\\"") + "\"}";

			sendMessageToAll(messageToSend);

		}
	}

	@OnClose
	public void onClose(Session session, CloseReason reason) {
		String nick = nickNames.get(session);
		conns.remove(session);
		nickNames.remove(session);
		if (nick != null) {
			removeUser(nick);
		}
		System.out.println("Closing a WebSocket due to "
				+ reason.getReasonPhrase());
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		String nick = nickNames.get(session);
		conns.remove(session);
		nickNames.remove(session);
		if (nick != null) {
			removeUser(nick);
		}
	}

	private void removeUser(String username) {
		String messageToSend = "{\"removeUser\":\"" + username + "\"}";
		sendMessageToAll(messageToSend);

	}

	private void sendMessageToAll(String message) {
		for (Session sock : conns) {
			try {
				sock.getBasicRemote().sendText(message);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}