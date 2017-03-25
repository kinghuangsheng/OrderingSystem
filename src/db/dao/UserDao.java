package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import db.pojo.User;

public interface UserDao {
	
	
	User selectByAccount(@Param("account")String account);
	User selectById(@Param("id")int id);

	List<Map<String, Object>> selectRestaurantUser(@Param("restaurantId")int restaurantId,
			@Param("key")String key, @Param("state")Integer state);
	
	int insertUser(User user);
	int updateUser(User user);
	int selectRestaurantState(int restaurantId);

}