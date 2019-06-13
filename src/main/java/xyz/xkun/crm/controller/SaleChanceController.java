package xyz.xkun.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xkun.crm.annotation.RequestPermission;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.model.ResultInfo;
import xyz.xkun.crm.po.SaleChance;
import xyz.xkun.crm.query.SaleChanceQuery;
import xyz.xkun.crm.service.SaleChanceService;
import xyz.xkun.crm.utils.LoginUserUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/5 18:49
 */
@Controller
@RequestMapping("saleChance")
public class SaleChanceController extends BaseController {

    @Autowired
    private SaleChanceService saleChanceService;

    /**
     * 进入营销机会查询
     *
     * @return
     */
    @RequestPermission(aclValue = "10")
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        String state = request.getParameter("state");
        if ("1".equals(state)) {
            return "cus_dev_plan";
        }
        return "sale_chance";
    }


    /**
     * 营销机会查询
     *
     * @param page
     * @param rows
     * @param query
     * @return
     */

    @RequestMapping("querySaleChancesByParams")
    @ResponseBody
    public Map<String, Object> querySaleChancesByParams(Integer page,
                                                        Integer rows, SaleChanceQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return saleChanceService.queryForPage(query);
    }

    @RequestPermission(aclValue = "101001")
    @RequestMapping("saveOrUpdateSaleChance")
    @ResponseBody
    public ResultInfo saveOrUpdateSaleChance(SaleChance saleChance, HttpServletRequest request) {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        saleChanceService.saveOrUpdateSaleChance(saleChance, userId);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("queryCustomerManagers")
    @ResponseBody
    public List<Map> queryCustomerManagers() {
        return saleChanceService.queryCustomerManagers();
    }

    @RequestPermission(aclValue = "101003")
    @RequestMapping("deleteSaleChanceBatch")
    @ResponseBody
    public ResultInfo deleteSaleChanceBatch(Integer[] ids) {
        saleChanceService.deleteBatch(ids);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("updateSaleChanceDevResult")
    @ResponseBody
    public ResultInfo updateSaleChanceDevResult(SaleChance saleChance) {
        saleChanceService.updateSaleChanceDevResult(saleChance);
        return success(CrmConstant.OPS_SUCCESS_CODE, CrmConstant.OPS_SUCCESS_MSG);
    }
}

