package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import bean.Page;
import db.pojo.Food;

public interface FoodDao {
	
	
	List<Map<String, Object>> selectFoodCount(@Param("restaurantId")int restaurantId, @Param("key")String key);
	List<Map<String, Object>> selectFood(@Param("restaurantId")int restaurantId, @Param("key")String key, @Param("page")Page page);
	
	int insertFood(Food food);
	int updateFood(Food food);
	int deleteFood(Food food);
	
	List<Integer> selectAllCategoryIds(@Param("restaurantId")int restaurantId);
	void insertFoodCategory(@Param("foodId")Integer foodId, @Param("categoryIdList")List<Integer> categoryIdList);
	void deleteFoodCategory(@Param("foodId")Integer foodId);
	List<Map<String, Object>>  selectFoodCategoryList(@Param("restaurantId")int restaurantId, @Param("foodId")Integer foodId);

}