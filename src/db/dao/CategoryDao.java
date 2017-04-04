package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import bean.Page;
import db.pojo.Category;

public interface CategoryDao {
	
	
	List<Map<String, Object>> selectCategory(@Param("restaurantId")int restaurantId, @Param("key")String key, @Param("page")Page page);
	int selectCategoryCount(@Param("restaurantId")int restaurantId, @Param("key")String key);
	
	int insertCategory(Category category);
	
	int updateCategory(Category newCategory);
	

}