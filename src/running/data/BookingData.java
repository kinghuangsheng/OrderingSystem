package running.data;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class BookingData {
	
	
	private Integer seatId;
	private String secret;
	private ArrayList<WebSocketSession> customerSession = new ArrayList<WebSocketSession>();

	public BookingData(Integer seatId) {
		this.seatId = seatId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	

	public synchronized void addCustomerSession(WebSocketSession session){
		customerSession.add(session);
	}
	
	public synchronized void removeCustomerSession(WebSocketSession session){
		customerSession.remove(session);
	}
	
	public synchronized void sendCustomerMessage(String msg){
		TextMessage textMessage = new TextMessage(msg);
		for(WebSocketSession session : customerSession){
			try {
				session.sendMessage(textMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
