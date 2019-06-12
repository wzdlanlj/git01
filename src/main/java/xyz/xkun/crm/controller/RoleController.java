package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.model.ResultInfo;
import xyz.xkun.crm.po.Role;
import xyz.xkun.crm.query.RoleQuery;
import xyz.xkun.crm.service.RoleService;

import java.util.List;
import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/10 13:00
 */
@RequestMapping("role")
@Controller
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("index")
    public String index() {
        return "role";
    }

    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map> queryAllRoles() {
        return roleService.queryAllRoles();
    }


    /**
     * 分页查询所有角色
     *
     * @param page
     * @param rows
     * @param query
     * @return
     */
    @RequestMapping("queryRolesByParams")
    @ResponseBody
    public Map<String, Object> queryRolesByParams(@RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer rows,
                                                  RoleQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return roleService.queryForPage(query);

    }

    /**
     * 添加或删除
     *
     * @param role
     * @return
     */
    @RequestMapping("saveOrUpdateRole")
    @ResponseBody
    public ResultInfo saveOrUpdateRole(Role role) {
        roleService.saveOrUpdateRole(role);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("doGrant")
    @ResponseBody
    public ResultInfo doGrant(Integer roleId, Integer[] moduleIds) {
        roleService.doGrant(roleId, moduleIds);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }
}
