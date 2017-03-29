package bean;

import common.util.JsonUtil;
import global.constant.Reason;

public class Response{
	
	private int code;
	private String msg;
	private String callback;
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
		if(callback == null){
			return JsonUtil.toJsonString(this);
		}else{
			String callbackTmp = callback;
			this.callback = null;
			return callbackTmp + "(" + JsonUtil.toJsonString(this) + ")";
		}
	}
	
	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

}
