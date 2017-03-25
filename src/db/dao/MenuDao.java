package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import db.pojo.Menu;

public interface MenuDao {
	
	
	List<Map<String, Object>> selectRoleMenu(@Param("roleId")int roleId);
	List<Map<String, Object>> selectMenuInterface(@Param("menuId")int menuId);
	List<Map<String, Object>> selectAllMenu();
	List<Integer> selectAllInterfaceIds();
	void updateMenu(@Param("menu")Menu menu);
	void deleteMenuInterface(@Param("menu")Menu menu);
	void insertMenuInterface(@Param("menu")Menu menu, @Param("menuInterfaceIds")List<Integer> menuInterfaceIds);
	

}