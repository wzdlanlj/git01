package xyz.xkun.crm.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.dao.RoleMapper;
import xyz.xkun.crm.po.Role;
import xyz.xkun.crm.utils.AssertUtil;

import java.util.Date;
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

    public List<Map> queryAllRoles() {
        return roleMapper.queryAllRoles();
    }

    public void saveOrUpdateRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名称为空");
        /**
         * 角色名唯一校验
         */
        role.setUpdateDate(new Date());
        Integer id = role.getId();
        if (null == id) {
            AssertUtil.isTrue(roleMapper.queryRoleByName(role.getRoleName()) > 0, "角色已经存在");
            role.setCreateDate(new Date());
            role.setIsValid(1);
            AssertUtil.isTrue(roleMapper.save(role) < 1, CrmConstant.OPS_FAILED_MSG);
        } else {
            //更新

            Role role2 = roleMapper.queryById(id);
            /**
             * 如果只更新备注时,没有修改用户名,不需要判断角色是否存在
             * 只有在id相同,名字不同时才做角色校验
             */
            if (!role2.getRoleName().equals(role.getRoleName())) {
                System.out.println("11111111111111111");
                System.out.println("11111111111111111");
                System.out.println("11111111111111111");
                System.out.println("11111111111111111");
                System.out.println(roleMapper.queryRoleByName(role.getRoleName()));
                AssertUtil.isTrue(roleMapper.queryRoleByName(role.getRoleName()) > 0, "角色已经存在");
            }
            AssertUtil.isTrue(roleMapper.update(role) < 1, CrmConstant.OPS_FAILED_MSG);
        }

    }

}
