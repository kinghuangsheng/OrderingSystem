package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import db.pojo.User;

public interface UserDao {
	
	
	User selectByAccount(String id);

	List<Map> selectAllUsers();
	
	int insertUser(User user);
	
	List<Map> selectManagerByAccountName(@Param("account")String account, @Param("name")String name);

}