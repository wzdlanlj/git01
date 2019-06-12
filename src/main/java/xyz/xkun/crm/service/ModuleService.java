package xyz.xkun.crm.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.dao.ModuleMapper;
import xyz.xkun.crm.dto.ModuleDto;
import xyz.xkun.crm.po.Module;
import xyz.xkun.crm.utils.AssertUtil;

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
        AssertUtil.isTrue(moduleMapper.queryByModuleName(module.getModuleName()) > 0, "模块名称不能重复");
        AssertUtil.isTrue(moduleMapper.queryByModuleOptValue(module.getParentOptValue()) > 0, "权限码不能重复");

    }

}
