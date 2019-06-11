package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.model.ResultInfo;
import xyz.xkun.crm.po.CusDevPlan;
import xyz.xkun.crm.po.SaleChance;
import xyz.xkun.crm.query.CusDevPlanQuery;
import xyz.xkun.crm.service.CusDevPlanService;
import xyz.xkun.crm.service.SaleChanceService;

import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/9 12:31
 */
@Controller
@RequestMapping("cusDevPlan")
public class CusDevPlanController extends BaseController {

    @Autowired
    private SaleChanceService saleChanceService;
    @Autowired
    private CusDevPlanService cusDevPlanService;

    //进入详情页并回显参数
    @RequestMapping("index")
    public String index(Integer sid, Model model) {
        SaleChance saleChance = saleChanceService.queryById(sid);
        model.addAttribute(saleChance);
        return "cus_dev_plan_detail";
    }

    //分页查询开发计划项
    @RequestMapping("queryCusDevPlansByParams")
    @ResponseBody
    public Map<String, Object> queryCusDevPlansByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            CusDevPlanQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return cusDevPlanService.queryForPage(query);
    }

    //开发计划项更新或者添加
    @RequestMapping("saveOrUpdateCusDevPlan")
    @ResponseBody
    public ResultInfo saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan, Integer sid) {
        cusDevPlanService.saveOrUpdateCusDevPlan(cusDevPlan, sid);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }
    //开发计划项删除
    @RequestMapping("deleteCusDevPlanBatch")
    @ResponseBody
    public ResultInfo deleteCusDevPlanBatch(Integer[] ids) {
        cusDevPlanService.deleteBatch(ids);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }
}
