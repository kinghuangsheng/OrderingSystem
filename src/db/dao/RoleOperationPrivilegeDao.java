package db.dao;

import java.util.List;

public interface RoleOperationPrivilegeDao {

	List<Integer> selectByRoleId(Integer id);
	
}