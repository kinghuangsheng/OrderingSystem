package bean.response;

import common.util.Object2JsonUtil;
import global.constant.Reason;

public class Response {
	
	private int code;
	private String describe;
	
	public void setReason(Reason reason){
		this.code = reason.getCode();
		this.describe = reason.getDescribe();
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
	public String toJsonString(){
		return Object2JsonUtil.toJsonString(this);
	}
	
	

}
