package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import db.pojo.RoleOperationPrivilege;

public interface RoleOperationPrivilegeDao {

	List<Map<String, Object>> selectPrivilegeInfo(@Param("roleId")Integer roleId);
	List<Integer> selectPrivilegeId(@Param("roleId")Integer roleId);
	
	int insertRoleIdPrivilegeIds(@Param("roleId")Integer roleId, @Param("privilegeIds")List<Integer> privilegeIds); 
	int insertRoleOperationPrivilege(@Param("roleOperationPrivileges")List<RoleOperationPrivilege> roleOperationPrivileges); 
}