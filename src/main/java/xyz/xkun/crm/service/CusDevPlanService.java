package xyz.xkun.crm.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.dao.CusDevPlanMapper;
import xyz.xkun.crm.po.CusDevPlan;
import xyz.xkun.crm.utils.AssertUtil;

import java.util.Date;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/9 13:36
 */
@Service
public class CusDevPlanService extends BaseService<CusDevPlan> {

    @Autowired
    private CusDevPlanMapper cusDevPlanMapper;

    public void saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan,Integer sid){
        /**
         * 1.参数校验
         * 2.补全参数
         * 3.通过id区分添加或者更新
         * 4.执行操作
         */
        checkCusDevPlanParams(cusDevPlan);

        cusDevPlan.setUpdateDate(new Date());

        Integer id = cusDevPlan.getId();

        if (null ==id){
            cusDevPlan.setCreateDate(new Date());
            cusDevPlan.setIsValid(1);
            cusDevPlan.setSaleChanceId(sid);//营销机会ID
            AssertUtil.isTrue(cusDevPlanMapper.save(cusDevPlan)<1, CrmConstant.OPS_FAILED_MSG);
        }else {
            AssertUtil.isTrue(cusDevPlanMapper.update(cusDevPlan)<1, CrmConstant.OPS_FAILED_MSG);
        }

    }

    private void checkCusDevPlanParams(CusDevPlan cusDevPlan) {
        AssertUtil.isTrue(null==(cusDevPlan.getPlanDate()),"计划日期为空");
        AssertUtil.isTrue(null==cusDevPlan.getPlanItem(),"计划内容为空");
        AssertUtil.isTrue(null==cusDevPlan.getExeAffect(),"计划结果为空");
    }
}


















