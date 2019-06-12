package xyz.xkun.crm.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.dao.ModuleMapper;
import xyz.xkun.crm.dao.PermissionMapper;
import xyz.xkun.crm.dao.RoleMapper;
import xyz.xkun.crm.po.Permission;
import xyz.xkun.crm.po.Role;
import xyz.xkun.crm.utils.AssertUtil;

import java.util.ArrayList;
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
    @Autowired
    public PermissionMapper permissionMapper;
    @Autowired
    public ModuleMapper moduleMapper;

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
                AssertUtil.isTrue(roleMapper.queryRoleByName(role.getRoleName()) > 0, "角色已经存在");
            }
            AssertUtil.isTrue(roleMapper.update(role) < 1, CrmConstant.OPS_FAILED_MSG);
        }

    }

    /**
     * 角色授权
     *
     * @param roleId
     * @param moduleIds
     */
    public void doGrant(Integer roleId, Integer[] moduleIds) {
        AssertUtil.isTrue(null == roleId, "角色Id不存在");
        AssertUtil.isTrue(null == roleMapper.queryById(roleId), "角色不存在");
        if (null != moduleIds && moduleIds.length > 0) {
            //角色逻辑操作
            //先查询权限,有->先删除再添加
            //先查询权限,无->直接添加
            Integer num = permissionMapper.queryModulesByRoleId(roleId);
            if (num > 0) {
                AssertUtil.isTrue(permissionMapper.deleteModulesByRoleId(roleId) < num, CrmConstant.OPS_FAILED_MSG);
            }
            List<Permission> permissions = new ArrayList<>();
            for (Integer m : moduleIds) {
                Permission permission = new Permission();
                permission.setRoleId(roleId);
                permission.setModuleId(m);
                permission.setUpdateDate(new Date());
                permission.setCreateDate(new Date());

                permission.setAclValue(moduleMapper.queryById(m).getOptValue());//权限码
                permissions.add(permission);
            }
            AssertUtil.isTrue(permissionMapper.saveBatch(permissions) < permissions.size(), CrmConstant.OPS_FAILED_MSG);
        }
    }

}
