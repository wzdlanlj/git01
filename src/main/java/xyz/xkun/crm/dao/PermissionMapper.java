package xyz.xkun.crm.dao;

import org.springframework.stereotype.Repository;
import xyz.xkun.crm.base.BaseDao;
import xyz.xkun.crm.po.Permission;

@Repository
public interface PermissionMapper extends BaseDao<Permission> {

    public Integer queryModulesByRoleId(Integer roleId);

    public Integer deleteModulesByRoleId(Integer roleId);

}