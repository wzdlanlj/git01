package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.dto.UserDto;
import xyz.xkun.crm.model.ResultInfo;
import xyz.xkun.crm.model.UserInfo;
import xyz.xkun.crm.po.User;
import xyz.xkun.crm.query.UserQuery;
import xyz.xkun.crm.service.UserService;
import xyz.xkun.crm.utils.LoginUserUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/4 10:42
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName, String userPwd) {
        UserInfo userInfo = userService.login(userName, userPwd);
        return success(CrmConstant.OPS_SUCCESS_CODE, "登录成功", userInfo);
    }

    @RequestMapping("updateUserPwd")
    @ResponseBody
    public ResultInfo updateUserPwd(String oldPwd, String newPwd, String confirmPwd, HttpServletRequest request) {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);//获取ID
        userService.updateUserPwd(oldPwd, newPwd, confirmPwd, userId);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }

    //用户权限管理页面
    //用户信息管理页
    @RequestMapping("index")
    public String index() {
        return "user";
    }

    //用户信息搜索
    @RequestMapping("queryUsersByParams")
    @ResponseBody
    public Map<String, Object> queryUsersByParams(@RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer rows,
                                                  UserQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return userService.queryForPage(query);
    }

    @RequestMapping("saveOrUpdateUser")
    @ResponseBody
    public ResultInfo saveOrUpdateUser(UserDto user, Integer[] roleIds){
        userService.saveOrUpdateUser(user,roleIds);
        return success(CrmConstant.OPS_SUCCESS_CODE,CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("deleteUsers")
    @ResponseBody
    public ResultInfo deleteUsers(Integer[] ids) {
        userService.deleteUsers(ids);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }
}
