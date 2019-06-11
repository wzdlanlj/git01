package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.xkun.crm.service.UserService;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/3 22:07
 */
@Controller
public class IndexController extends BaseController {


    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index() {
        return "index";
    }
}
