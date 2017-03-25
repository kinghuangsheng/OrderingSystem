package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import bean.Page;
import db.pojo.Interface;

public interface InterfaceDao {
	
	
	List<Map<String, Object>> selectInterfaceList(@Param("key")String key, @Param("page")Page page);
	int selectInterfaceCount(@Param("key")String key);
	int insertInterface(@Param("interface") Interface i);
	

}