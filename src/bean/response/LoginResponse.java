package bean.response;

import global.constant.Reason;

public class LoginResponse extends AbsResponse {

	
	public LoginResponse() {
		super(null);
	}


	public LoginResponse(Reason reason) {
		super(reason);
	}


}
