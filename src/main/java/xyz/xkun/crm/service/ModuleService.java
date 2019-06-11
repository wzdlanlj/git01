package xyz.xkun.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.dao.ModuleMapper;
import xyz.xkun.crm.dto.ModuleDto;
import xyz.xkun.crm.po.Module;

import java.util.List;

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


}
