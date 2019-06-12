package xyz.xkun.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.xkun.crm.base.BaseDao;
import xyz.xkun.crm.dto.ModuleDto;
import xyz.xkun.crm.po.Module;

import java.util.List;
import java.util.Map;

@Repository
public interface ModuleMapper extends BaseDao<Module> {

    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId);

    public List<Map> queryModulesByGrade(Integer grade);

    public Integer queryByModuleName(String moduleName);

    public Integer queryByModuleOptValue(String optValue);
}