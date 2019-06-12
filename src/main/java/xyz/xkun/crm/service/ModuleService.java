package xyz.xkun.crm.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.dao.ModuleMapper;
import xyz.xkun.crm.dao.PermissionMapper;
import xyz.xkun.crm.dto.ModuleDto;
import xyz.xkun.crm.po.Module;
import xyz.xkun.crm.utils.AssertUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/11 18:36
 */
@Service
public class ModuleService extends BaseService<Module> {

    @Autowired
    public ModuleMapper moduleMapper;
    @Autowired
    public PermissionMapper permissionMapper;

    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId) {
        return moduleMapper.queryAllModuleByRoleId(roleId);
    }

    //层级查询模块
    public List<Map> queryModulesByGrade(Integer grade) {
        return moduleMapper.queryModulesByGrade(grade);
    }

    //添加或者更新
    public void saveOrUpdateModule(Module module) {
        /**
         * 1.校验参数
         * 2.补全参数
         * 3.判断添加或者更新
         * 4.执行操作
         */
        checkModuleParams(module);

        Integer id = module.getId();
        module.setUpdateDate(new Date());

        if (null == id) {
            module.setCreateDate(new Date());
            module.setIsValid((byte) 1);
            AssertUtil.isTrue(moduleMapper.save(module) < 1, CrmConstant.OPS_FAILED_MSG);
        }

    }

    private void checkModuleParams(Module module) {
        /**
         * 1.非空校验
         */
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "模块名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "权限码不能为空不能为空");
        /**
         * 唯一校验
         */
        AssertUtil.isTrue(moduleMapper.queryByModuleName(module.getModuleName()) > 0, "模块名称已存在");
        AssertUtil.isTrue(moduleMapper.queryByModuleOptValue(module.getOptValue()) > 0, "权限码已存在");

        Integer grade = module.getGrade();//菜单层级   验证格式
        AssertUtil.isTrue(null == grade, "菜单层级为空");

        /**
         * 0    2
         * 1    4
         * 2    6
         */

        Integer len = (grade + 1) * 2;
        AssertUtil.isTrue(len != module.getOptValue().length(), "权限码格式不正确,应为" + len + "位");

        if (grade > 0) {
            Module parentModule = moduleMapper.queryById(module.getParentId());//父模块
            Integer parentGrade = parentModule.getGrade();//父模块层级
            AssertUtil.isTrue(grade - parentGrade != 1, "菜单层级不正确");
            String parentModuleOptValue = parentModule.getOptValue();
            AssertUtil.isTrue(module.getOptValue().indexOf(parentModuleOptValue) != 0, "权限格式不正确,格式应为:" + parentModuleOptValue);
        } else {

        }
    }

    public void deleteModule(Integer[] ids) {
        AssertUtil.isTrue(null == ids, "请选择要删除的模块");

        for (Integer moduleId : ids) {
            Module module = moduleMapper.queryById(moduleId);
            /**
             * 当前表联删除
             */
            String optValue = module.getOptValue();
            Integer total = moduleMapper.selectTotalByOptValue(optValue);
            if (total > 0) {
                AssertUtil.isTrue(moduleMapper.deleteBatchByOptValue(optValue) < total, CrmConstant.OPS_FAILED_MSG);
            }
            /**
             * 权限表级联删除
             */
            Integer total2 = permissionMapper.queryModulesByAclValue(optValue);
            if (total2 > 0) {
                AssertUtil.isTrue(permissionMapper.deleteModulesByAclValue(optValue) < total2, CrmConstant.OPS_FAILED_MSG);
            }
        }
    }

}















