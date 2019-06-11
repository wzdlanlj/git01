package xyz.xkun.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.dao.RoleMapper;
import xyz.xkun.crm.po.Role;

import java.util.List;
import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/10 12:58
 */
@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    public RoleMapper roleMapper;

    public List<Map> queryAllRoles(){
        return roleMapper.queryAllRoles();
    }
}
