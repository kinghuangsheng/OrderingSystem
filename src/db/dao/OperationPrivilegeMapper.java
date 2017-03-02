package db.dao;

import db.pojo.OperationPrivilege;

public interface OperationPrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OperationPrivilege record);

    int insertSelective(OperationPrivilege record);

    OperationPrivilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperationPrivilege record);

    int updateByPrimaryKey(OperationPrivilege record);
}