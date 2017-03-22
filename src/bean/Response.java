package bean;

import common.util.JsonUtil;
import global.constant.Reason;

public class Response{
	
	private int code;
	private String msg;
	private Object data;
	
	public void setReason(Reason reason){
		this.code = reason.getCode();
		this.msg = reason.getMsg();
	}
	
	public Response(){
		setReason(Reason.SUCCESS);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
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
		return JsonUtil.toJsonString(this);
	}
	
	

}
