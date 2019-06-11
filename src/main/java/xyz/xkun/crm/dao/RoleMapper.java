package xyz.xkun.crm.dao;

import org.springframework.stereotype.Repository;
import xyz.xkun.crm.base.BaseDao;
import xyz.xkun.crm.po.Role;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper extends BaseDao<Role> {

    public List<Map> queryAllRoles();
}