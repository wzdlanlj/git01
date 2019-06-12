package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.po.User;
import xyz.xkun.crm.service.UserService;
import xyz.xkun.crm.utils.LoginUserUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/4 9:43
 */
@Controller
public class MainController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("main")
    public String index(HttpServletRequest request) throws Exception {
        //查询用户,存入request作用域
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.queryById(userId);
        request.setAttribute("user", user);
        //查询权限列表
        List<String> permissions = userService.queryAllAclValueByUserId(userId);
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println(permissions);
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println("=======================");
        request.getSession().setAttribute(CrmConstant.USER_PERMISSIONS, permissions);

        return "main";
    }
}
