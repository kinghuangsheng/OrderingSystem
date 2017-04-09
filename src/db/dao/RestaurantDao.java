package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import bean.Page;
import db.pojo.Restaurant;

public interface RestaurantDao {
	
	
	int selectRestaurantCount(@Param("key")String key, @Param("state")Integer state, @Param("type")Integer type);
	List<Map<String, Object>> selectRestaurant(@Param("key")String key, @Param("state")Integer state, @Param("type")Integer type, @Param("page")Page page);
	
	Restaurant selectRestaurantById(@Param("id")int id);
	
	int insertRestaurant(Restaurant restaurant);
	int updateRestaurant(Restaurant restaurant);
	int setRestaurantState(@Param("id")int id, @Param("state")int state);

}