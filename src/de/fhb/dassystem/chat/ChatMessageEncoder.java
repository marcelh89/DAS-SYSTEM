package de.fhb.dassystem.chat;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.json.JSONObject;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(final ChatMessage chatMessage) throws EncodeException {
		JSONObject obj = new JSONObject();
		obj.append("message", chatMessage.getMessage());
		obj.append("sender", chatMessage.getSender());
		obj.append("received", chatMessage.getReceived());

		return obj.toString();
	}
}
