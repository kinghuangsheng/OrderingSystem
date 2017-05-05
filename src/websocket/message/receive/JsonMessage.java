package websocket.message.receive;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

import annotation.AbsWebSocketMessageImpl;
import common.util.AnnotationClassFindUtil;
import common.util.JsonUtil;

public class JsonMessage {
	
	private static HashMap<Integer, Class<?>> cmdClassMap = new HashMap<Integer, Class<?>>();
	
	static {
		List<Class<?>> classes = AnnotationClassFindUtil.findClassByAnnotationNameInPackageName(AbsWebSocketMessageImpl.class, "websocket.message.receive");
		for(Class clazz : classes){
			try {
				AbstractMessage abstractMessage = (AbstractMessage)clazz.newInstance();
				AbsWebSocketMessageImpl[] absWebSocketMessageImpl = abstractMessage.getClass().getAnnotationsByType(AbsWebSocketMessageImpl.class);
				cmdClassMap.put(absWebSocketMessageImpl[0].cmd(), abstractMessage.getClass());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

	private int cmd;
	private String data;
	
	public void handle(WebSocketSession session){
		try {
			Class clazz = cmdClassMap.get(cmd);
			AbstractMessage abstractMessage = (AbstractMessage)JsonUtil.parseObject(data, clazz);
			abstractMessage.handle(session);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	

}
