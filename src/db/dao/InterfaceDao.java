package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import bean.Page;

public interface InterfaceDao {
	
	
	List<Map<String, Object>> selectInterFaceList(@Param("key")String key, @Param("page")Page page);
	int selectInterFaceCount(@Param("key")String key);
	

}