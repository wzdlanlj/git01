package xyz.xkun.crm.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.dao.SaleChanceMapper;
import xyz.xkun.crm.dao.UserMapper;
import xyz.xkun.crm.po.SaleChance;
import xyz.xkun.crm.po.User;
import xyz.xkun.crm.utils.AssertUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/5 21:09
 */
@Service
public class SaleChanceService extends BaseService<SaleChance> {

    @Autowired
    private SaleChanceMapper saleChanceMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加或更新
     *
     * @param saleChance
     */
    public void saveOrUpdateSaleChance(SaleChance saleChance, Integer userId) {
        //1.校验参数
        //2.补全参数
        //3.通过ID区分是添加或者更新
        //4.执行sql操作
        checkSaleChanceParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());

        if (null != saleChance.getAssignMan()) {
            saleChance.setAssignTime(new Date());
            saleChance.setState(1);//已分配
        } else {
            saleChance.setState(0);//未分配
        }
        saleChance.setUpdateDate(new Date());

        Integer id = saleChance.getId();
        if (null == id) {
            saleChance.setState(0);//未分配
            saleChance.setDevResult(0);//未开发
            saleChance.setIsValid(1);//有效数据
            saleChance.setCreateDate(new Date());//创建时间
            User user = userMapper.queryById(userId);
            saleChance.setCreateMan(user.getUserName());//创建人
            AssertUtil.isTrue(saleChanceMapper.save(saleChance) < 1, CrmConstant.OPS_FAILED_MSG);
        } else {
            AssertUtil.isTrue(saleChanceMapper.update(saleChance) < 1, CrmConstant.OPS_FAILED_MSG);
        }
    }

    private void checkSaleChanceParams(String customerName, String linkMan, String linkPhone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName), "客户名称为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan), "联系人为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone), "联系电话为空");
    }

    /**
     * 查询所有客户经理
     *
     * @return
     */
    public List<Map> queryCustomerManagers() {
        return saleChanceMapper.queryCustomerManagers();
    }


    public Integer updateSaleChanceDevResult(SaleChance saleChance){
        return saleChanceMapper.updateSaleChanceDevResult(saleChance);
    }
}
