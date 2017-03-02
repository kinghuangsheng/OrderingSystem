package bean.response;

import java.util.List;

public class GetPrivilegeResponse extends Response{
	
	private List<Integer> privileges;

	public List<Integer> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Integer> privileges) {
		this.privileges = privileges;
	}
}
