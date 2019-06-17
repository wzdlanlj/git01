package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xkun.crm.po.Customer;
import xyz.xkun.crm.query.CustomerOrderQuery;
import xyz.xkun.crm.service.CustomerOrderService;
import xyz.xkun.crm.service.CustomerService;

import java.util.Map;

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

    @Autowired
    private CustomerOrderService customerOrderService;

    @RequestMapping("index")
    public String index(Integer cusId, Model model) {
        Customer customer = customerService.queryById(cusId);
        model.addAttribute(customer);
        return "customer_order";
    }

    @RequestMapping("queryCustomerOrdersByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerOrdersByParams(@RequestParam(defaultValue = "1") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer rows,
                                                           CustomerOrderQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerOrderService.queryForPage(query);
    }
}
