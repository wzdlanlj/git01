package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.xkun.crm.po.Customer;
import xyz.xkun.crm.service.CustomerService;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/13 21:25
 */
@Controller
@RequestMapping("customerOrder")
public class CustomerOrderController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("index")
    public String index(Integer cusId, Model model) {

        Customer customer = customerService.queryById(cusId);
        model.addAttribute(customer);

        return "customer_order";
    }
}
