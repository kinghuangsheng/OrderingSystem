package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import db.pojo.Menu;

public interface MenuDao {
	
	
	List<Map<String, Object>> selectRoleMenu(@Param("roleId")int roleId);
	List<Map<String, Object>> selectAuthorizedRoleMenu(@Param("parentRoleId")int parentRoleId, @Param("roleId")int roleId);
	List<Map<String, Object>> selectMenuInterface(@Param("menuId")int menuId);
	List<Map<String, Object>> selectAllMenu();
	List<Integer> selectAllInterfaceIds();
	int updateMenu(@Param("menu")Menu menu);
	int insertMenu(@Param("menu")Menu menu);
	int deleteMenu(@Param("menu")Menu menu);
	int deleteMenuInterface(@Param("menu")Menu menu);
	int deleteRoleMenu(@Param("menu")Menu menu);
	int insertRoleMenu(@Param("menu")Menu menu, @Param("roleId")int roleId);
	int insertMenuInterface(@Param("menu")Menu menu, @Param("menuInterfaceIds")List<Integer> menuInterfaceIds);
	

}