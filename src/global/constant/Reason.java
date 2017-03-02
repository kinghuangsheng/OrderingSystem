package global.constant;

public enum Reason {

	USER_NOT_EXIST(1, "用户不存在"),
	PASSW0RD_ERROR(2, "密码错误"),
	INTERNAL_ERROR(3, "系统内部错误"),
	HAS_NO_PERSSION(4, "没有权限");

	private int code;
	private String describe;

	private Reason(int code, String describe) {
		this.code = code;
		this.describe = describe;
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
