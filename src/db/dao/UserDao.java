package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import bean.Page;
import db.pojo.User;

public interface UserDao {
	
	
	User selectByAccount(@Param("account")String account, @Param("restaurantId")Integer restaurantId);
	User selectById(@Param("id")int id);

	List<Map<String, Object>> selectRestaurantUser(@Param("restaurantId")int restaurantId,
			@Param("key")String key, @Param("state")Integer state, @Param("type")Integer type, @Param("page")Page page);
	int selectRestaurantUserCount(@Param("restaurantId")int restaurantId,
			@Param("key")String key, @Param("state")Integer state, @Param("type")Integer type);
	
	int insertUser(User user);
	int updateUser(User user);
	int selectRestaurantState(int restaurantId);

}