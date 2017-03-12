package bean.response;

import common.util.Object2JsonUtil;
import global.constant.Reason;

public class Response<T extends Object>{
	
	private int code;
	private String msg;
	private T object;
	
	public Response(Reason reason){
		this.code = reason.getCode();
		this.msg = reason.getMsg();
	}
	
	public Response(){
		this(Reason.SUCCESS);
	}
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String toJsonString(){
		return Object2JsonUtil.toJsonString(this);
	}
	
	

}
