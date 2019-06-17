package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xkun.crm.query.OrderDetailsQuery;
import xyz.xkun.crm.service.OrderDetailsService;

import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/16 19:34
 */
@Controller
@RequestMapping("orderDetails")
public class OrderDetailsController extends BaseController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @RequestMapping("queryOrderDetailsByParams")
    @ResponseBody
    public Map<String, Object> queryOrderDetailsByParams(@RequestParam(defaultValue = "1") Integer page,
                                                         @RequestParam(defaultValue = "10") Integer rows,
                                                         OrderDetailsQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return orderDetailsService.queryForPage(query);

    }
}








