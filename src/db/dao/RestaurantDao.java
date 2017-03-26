package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import bean.Page;
import db.pojo.Restaurant;

public interface RestaurantDao {
	
	
	int selectRestaurantCount(@Param("key")String key, @Param("state")Integer state);
	List<Map<String, Object>> selectRestaurant(@Param("key")String key, @Param("state")Integer state, @Param("page")Page page);
	
	int insertRestaurant(Restaurant restaurant);
	int updateRestaurant(Restaurant restaurant);
	int deleteRestaurant(@Param("id")int id, @Param("state")int state);

}