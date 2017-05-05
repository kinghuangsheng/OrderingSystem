package websocket.message.receive;

import org.springframework.web.socket.WebSocketSession;

import global.constant.Constant;
import running.data.BookingData;


public abstract class AbstractMessage {
	
	public abstract void handle(WebSocketSession session);

	public void getBookingData(WebSocketSession session){
		session.getAttributes().get(Constant.MapKey.BOOKING_DATA);
	}
	
	public void setBookingData(WebSocketSession session, BookingData bookingData){
		session.getAttributes().put(Constant.MapKey.BOOKING_DATA, bookingData);
	}
	
}
