package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import db.pojo.User;

public interface RestaurantDao {
	
	
	List<Map> selectRestaurant(@Param("name")String name, @Param("license")String license);

}