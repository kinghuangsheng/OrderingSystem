package db.dao;

import java.util.List;
import java.util.Map;

import db.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    User selectByAccount(String id);
    
    List<Map> selectAllUsers();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}