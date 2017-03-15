package global.constant;

public enum Reason {

	SUCCESS(0, "成功"),
	USER_NOT_EXIST(1, "用户不存在"),
	PASSW0RD_ERROR(2, "密码错误"),
	INTERNAL_ERROR(3, "系统内部错误"),
	HAS_NO_PERSSION(4, "没有权限"),
	LICENSE_REPEATED(5, "营业执照已存在"),
	ACCOUNT_REPEATED(6, "账号已存在"),
	ERR_ARG(7, "参数有误");

	private int code;
	private String msg;

	private Reason(int code, String msg) {
		this.code = code;
		this.msg = msg;
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


}
