package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MenuDao {
	
	
	List<Map<String, Object>> selectRoleMenu(@Param("roleId")int roleId);
	List<Map<String, Object>> selectAllMenu();
	

}