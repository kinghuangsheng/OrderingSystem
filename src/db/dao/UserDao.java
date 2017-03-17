package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import db.pojo.User;

public interface UserDao {
	
	
	User selectByAccount(String id);

	List<Map<String, Object>> selectRestaurantUser(@Param("restaurantId")int restaurantId,
			@Param("key")String key, @Param("notInRoleIdString")String notInRoleIdString);
	
	int insertUser(User user);

}