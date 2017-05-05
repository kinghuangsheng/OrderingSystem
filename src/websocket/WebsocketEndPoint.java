package websocket;

import org.apache.log4j.Logger;
import org.springframework.web.socket.AbstractWebSocketMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import common.util.JsonUtil;
import controller.AbsController;
import global.constant.Constant;
import running.data.BookingData;
import websocket.message.receive.JsonMessage;  
  
public class WebsocketEndPoint extends TextWebSocketHandler {  
  
	private static Logger logger = Logger.getLogger(AbsController.class);
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("afterConnectionClosed");  
		super.afterConnectionClosed(session, status);
		if(session.getAttributes().containsKey(Constant.MapKey.BOOKING_DATA)){
			BookingData bookingData = (BookingData) session.getAttributes().get(Constant.MapKey.BOOKING_DATA);
			bookingData.removeCustomerSession(session);
		}
		
	}
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("afterConnectionEstablished");  
		super.afterConnectionEstablished(session);
	}
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		logger.info("handleTransportError");  
		super.handleTransportError(session, exception);
	}
    @Override  
    protected void handleTextMessage(WebSocketSession session,  
            TextMessage message) throws Exception {  
        super.handleTextMessage(session, message);  
        JsonMessage jsonMessage = JsonUtil.parseObject(message.getPayload(), JsonMessage.class);
        jsonMessage.handle(session);
    }  
}  