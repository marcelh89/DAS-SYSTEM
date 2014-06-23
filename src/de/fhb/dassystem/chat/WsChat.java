package de.fhb.dassystem.chat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{room}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class WsChat {
	private final Logger log = Logger.getLogger(getClass().getName());

	private static Set<Session> conns = java.util.Collections
			.synchronizedSet(new HashSet<Session>());
	private static Map<Session, String> nickNames = new ConcurrentHashMap<Session, String>();

	private Session currentSession;

	public WsChat() {
		System.out.println("Constructed!");
	}

	@OnOpen
	public void open(final Session session, @PathParam("room") final String room) {
		log.info("session openend and bound to room: " + room);
		session.getUserProperties().put("room", room);
		conns.add(session);
		this.currentSession = session;
	}

	@OnMessage
	public void onMessage(final Session session, final ChatMessage chatMessage) {

		System.out.println("Received: " + chatMessage.toString());
		if (!nickNames.containsKey(currentSession)) {
			// No nickname has been assigned by now
			// the first message is the nickname
			// escape the " character first
			// message = message.replace("\"", "\\\"");

			// broadcast all the nicknames to him
			for (String nick : nickNames.values())
				sendMessageToAllInRoom(new ChatMessage("addUser" + nick));

			// Register the nickname with the
			nickNames.put(currentSession, chatMessage.getMessage());

			sendMessageToAllInRoom(chatMessage);

		} else {

			sendMessageToAllInRoom(chatMessage);

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
		sendMessageToAllInRoom(new ChatMessage(messageToSend));
	}

	// private void sendMessageToAll(String message) {
	// for (Session sock : conns) {
	// try {
	// sock.getBasicRemote().sendText(message);
	// } catch (IOException ex) {
	// ex.printStackTrace();
	// }
	// }
	// }

	private void sendMessageToAllInRoom(ChatMessage message) {
		String room = (String) currentSession.getUserProperties().get("room");
		try {
			for (Session s : conns) {
				if (s.isOpen()
						&& room.equals(s.getUserProperties().get("room"))) {
					s.getBasicRemote().sendObject(message);
				}
			}
		} catch (IOException | EncodeException e) {
			log.log(Level.WARNING, "onMessage failed", e);
		}
	}

}
