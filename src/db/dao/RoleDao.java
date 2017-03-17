package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import db.pojo.Role;
import db.pojo.User;

public interface RoleDao {
	
	
	List<Map<String, Object>> selectRole(@Param("restaurantId")int restaurantId, @Param("key")String key);
	
	int insertRole(Role role);
	
	int selectRoleIdNum(@Param("restaurantId")int restaurantId, @Param("roleId")int roleId);

}