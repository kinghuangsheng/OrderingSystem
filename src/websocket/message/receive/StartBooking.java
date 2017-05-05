package websocket.message.receive;

import org.springframework.web.socket.WebSocketSession;

import annotation.AbsWebSocketMessageImpl;
import running.data.BookingData;
import running.data.GlobalData;

@AbsWebSocketMessageImpl(cmd = 1)
public class StartBooking extends AbstractMessage {
	
	private int restaurantId;
	private int seatId;
	private String secret;
	
	@Override
	public void handle(WebSocketSession session) {
		System.out.println(secret);
		
		BookingData bookingData = GlobalData.getBookingData(seatId);
		bookingData.addCustomerSession(session);
		bookingData.sendCustomerMessage("login");
		
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	
}
