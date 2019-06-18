package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.model.ResultInfo;
import xyz.xkun.crm.po.CustomerServe;
import xyz.xkun.crm.query.CustomerServeQuery;
import xyz.xkun.crm.service.CustomerServeService;
import xyz.xkun.crm.utils.LoginUserUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/17 19:16
 */
@Controller
@RequestMapping("customerServe")
public class CustomerServeController extends BaseController {

    @Autowired
    private CustomerServeService customerServeService;

    @RequestMapping("index/{state}")
    public String index(@PathVariable Integer state) {
        switch (state) {
            case 1:
                return "customer_serve_create";
            case 2:
                return "customer_serve_assign";
            case 3:
                return "customer_serve_proce";
            case 4:
                return "customer_serve_feed_back";
            case 5:
                return "customer_serve_archive";
            default:
                return "error";
        }
    }

    @RequestMapping("saveOrUpdateCustomerServe")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomerServe(CustomerServe customerServe, HttpServletRequest request) {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        customerServeService.saveOrUpdateCustomerServe(customerServe, userId);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("queryCustomerServesByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerServesByParams(@RequestParam(defaultValue = "1") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer rows,
                                                           CustomerServeQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerServeService.queryForPage(query);
    }
}
