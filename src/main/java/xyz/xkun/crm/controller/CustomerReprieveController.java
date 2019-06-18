package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.model.ResultInfo;
import xyz.xkun.crm.po.CustomerLoss;
import xyz.xkun.crm.po.CustomerReprieve;
import xyz.xkun.crm.query.CustomerReprieveQuery;
import xyz.xkun.crm.service.CustomerLossService;
import xyz.xkun.crm.service.CustomerReprieveService;

import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/17 12:34
 */
@RequestMapping("customerReprieve")
@Controller
public class CustomerReprieveController extends BaseController {

    @Autowired
    private CustomerLossService customerLossService;

    @Autowired
    private CustomerReprieveService customerReprieveService;

    @RequestMapping("index")
    public String index(Integer lossId, Model model) {
        CustomerLoss customerLoss = customerLossService.queryById(lossId);
        model.addAttribute(customerLoss);
        return "customer_loss_reprieve";
    }

    @RequestMapping("saveOrUpdateCustomerReprieve")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomerReprieve(CustomerReprieve customerReprieve) {
        customerReprieveService.saveOrUpdateCustomerReprieve(customerReprieve);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("delReprieve")
    @ResponseBody
    public ResultInfo delReprieve(Integer id) {
        customerReprieveService.delete(id);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }


    @RequestMapping("queryCustomerReprievesByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerReprievesByParams(@RequestParam(defaultValue = "1") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer rows,
                                                              CustomerReprieveQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerReprieveService.queryForPage(query);
    }

}
