package bean.response;

import global.constant.Reason;

public class AbsResponse {
	
	private int code;
	private String describe;
	
	public AbsResponse(Reason reason) {
		if(reason != null){
			this.code = reason.getCode();
			this.describe = reason.getDescribe();
		}
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
	
	
	

}
