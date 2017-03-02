package db.dao;

import java.util.List;

import db.pojo.RoleOperationPrivilege;

public interface RoleOperationPrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleOperationPrivilege record);

    int insertSelective(RoleOperationPrivilege record);

    RoleOperationPrivilege selectByPrimaryKey(Integer id);
    
    List<Integer> selectByRoleId(Integer id);

    int updateByPrimaryKeySelective(RoleOperationPrivilege record);

    int updateByPrimaryKey(RoleOperationPrivilege record);
}