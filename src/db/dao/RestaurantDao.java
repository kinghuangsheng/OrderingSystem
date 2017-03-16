package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import db.pojo.Restaurant;

public interface RestaurantDao {
	
	
	List<Map<String, Object>> selectRestaurant(@Param("key")String key, @Param("roleId")int roleId);
	int insertRestaurant(Restaurant restaurant);

}