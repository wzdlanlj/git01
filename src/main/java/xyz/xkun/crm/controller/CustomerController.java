package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.model.ResultInfo;
import xyz.xkun.crm.po.Customer;
import xyz.xkun.crm.query.CustomerQuery;
import xyz.xkun.crm.service.CustomerService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CustomerService customerService;

    @RequestMapping("index")
    public String index() {
        return "customer";
    }

    @RequestMapping("queryCustomersByParams")
    @ResponseBody
    public Map<String, Object> queryCustomersByParams(@RequestParam(defaultValue = "1") Integer page,
                                                      @RequestParam(defaultValue = "10") Integer rows,
                                                      CustomerQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerService.queryForPage(query);
    }

    @RequestMapping("queryDataDicsByDicName")
    @ResponseBody
    public List<Map> queryDataDicsByDicName(String dicName) {
        return customerService.queryDataDicsByDicName(dicName);
    }

    @RequestMapping("saveOrUpdateCustomer")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomer(Customer customer) {
        customerService.saveOrUpdateCustomer(customer);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("deleteCustomer")
    @ResponseBody
    public ResultInfo deleteCustomer(Integer[] ids) {
        System.out.println(Arrays.toString(ids));
        customerService.deleteBatch(ids);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("addLossCustomerds")
    @ResponseBody
    public ResultInfo addLossCustomerds() {
        customerService.addLossCustomerds();
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }
}
