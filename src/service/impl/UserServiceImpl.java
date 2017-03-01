package service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import db.dao.UserMapper;
import db.pojo.User;
import service.IUserService;

@Service("userService")  
public class UserServiceImpl implements IUserService {  
    @Resource  
    private UserMapper userMapper;  
    @Override  
    public User getUserById(int userId) {  
        return this.userMapper.selectByPrimaryKey(userId);  
    }  
  
}  