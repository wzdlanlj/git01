package xyz.xkun.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/13 10:50
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @RequestMapping("index")
    public String index() {
        return "customer";
    }
}
