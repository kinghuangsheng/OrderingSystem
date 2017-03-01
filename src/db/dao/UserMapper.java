package db.dao;

import db.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    User selectByAccount(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}