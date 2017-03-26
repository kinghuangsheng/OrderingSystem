package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import bean.Page;
import db.pojo.Role;

public interface RoleDao {
	
	
	List<Map<String, Object>> selectRole(@Param("restaurantId")int restaurantId, @Param("key")String key, @Param("page")Page page);
	int selectRoleCount(@Param("restaurantId")int restaurantId, @Param("key")String key);
	
	int insertRole(Role role);
	
	int insertRoleMenu(@Param("roleId")int roleId, @Param("menus")List<Integer> menus);
	
	int selectRoleIdNum(@Param("restaurantId")int restaurantId, @Param("roleId")int roleId);
	
	List<String> selectRoleInterface(@Param("roleId")int roleId);
	
	List<Integer> selectRoleMenuIds(@Param("roleId")int roleId);
	
	int updateRole(Role newRole);
	
	int deleteRoleMenu(int roldId);

}