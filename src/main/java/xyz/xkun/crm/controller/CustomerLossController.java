package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.model.ResultInfo;
import xyz.xkun.crm.po.CustomerLoss;
import xyz.xkun.crm.query.CustomerLossQuery;
import xyz.xkun.crm.service.CustomerLossService;

import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/17 10:36
 */
@RequestMapping("customerLoss")
@Controller
public class CustomerLossController extends BaseController{

    @Autowired
    private CustomerLossService customerLossService;

    @RequestMapping("index")
    public String index(){
        return "customer_loss";
    }

    @RequestMapping("queryCustomerLossByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerLossByParams(@RequestParam(defaultValue = "1") Integer page,
                                                         @RequestParam(defaultValue = "10") Integer rows,
                                                         CustomerLossQuery customerLossQuery){
        customerLossQuery.setPageNum(page);
        customerLossQuery.setPageSize(rows);
        return customerLossService.queryForPage(customerLossQuery);
    }

    @RequestMapping("updateCustomerLoss")
    @ResponseBody
    public ResultInfo updateCustomerLoss(CustomerLoss customerLoss){
        customerLossService.update(customerLoss);
        return success(CrmConstant.OPS_SUCCESS_CODE,CrmConstant.OPS_SUCCESS_MSG);
    }
}









