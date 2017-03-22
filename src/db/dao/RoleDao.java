package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import db.pojo.Role;

public interface RoleDao {
	
	
	List<Map<String, Object>> selectRole(@Param("restaurantId")int restaurantId, @Param("key")String key);
	
	int insertRole(Role role);
	
	int insertRoleMenu(@Param("roleId")int roleId, @Param("menus")List<Integer> menus);
	
	int selectRoleIdNum(@Param("restaurantId")int restaurantId, @Param("roleId")int roleId);
	
	List<Integer> selectRoleInterface(@Param("roleId")int roleId);

}