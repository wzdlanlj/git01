package xyz.xkun.crm.dao;

import org.springframework.stereotype.Repository;
import xyz.xkun.crm.base.BaseDao;
import xyz.xkun.crm.dto.ModuleDto;
import xyz.xkun.crm.po.Module;

import java.util.List;

@Repository
public interface ModuleMapper extends BaseDao<Module> {
    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId);
}