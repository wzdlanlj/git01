package xyz.xkun.crm.dao;

import org.springframework.stereotype.Repository;
import xyz.xkun.crm.base.BaseDao;
import xyz.xkun.crm.po.UserRole;

@Repository
public interface UserRoleMapper extends BaseDao<UserRole> {
    public Integer queryUserRolesByUserId(Integer userId);

    public Integer deleteUserRolesByUserId(Integer userId);
}