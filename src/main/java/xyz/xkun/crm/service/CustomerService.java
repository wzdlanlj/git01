package xyz.xkun.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.dao.CustomerLossMapper;
import xyz.xkun.crm.dao.CustomerMapper;
import xyz.xkun.crm.po.Customer;
import xyz.xkun.crm.po.CustomerLoss;
import xyz.xkun.crm.query.CustomerQuery;
import xyz.xkun.crm.utils.AssertUtil;
import xyz.xkun.crm.utils.MathUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/13 11:10
 */
@Service
public class CustomerService extends BaseService<Customer> {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerLossMapper customerLossMapper;

    public List<Map> queryDataDicsByDicName(String dicName) {
        return customerMapper.queryDataDicsByDicName(dicName);
    }

    public void saveOrUpdateCustomer(Customer customer) {
        //参数校验
        checkCustomerParams(customer);
        customer.setUpdateDate(new Date());
        Integer id = customer.getId();
        if (null == id) {
            customer.setState(0);//未流失
            customer.setIsValid(1);//有效
            customer.setCreateDate(new Date());
            customer.setKhno(MathUtil.genereateKhCode());//生成订单号
            AssertUtil.isTrue(customerMapper.save(customer) > 1, CrmConstant.OPS_FAILED_MSG);
        } else {
            AssertUtil.isTrue(customerMapper.update(customer) > 1, CrmConstant.OPS_FAILED_MSG);
        }
    }

    //参数校验
    private void checkCustomerParams(Customer customer) {

    }

    //查询流失客户
    public void addLossCustomerds() {
        /**
         * 1.查询所有流失客户
         * 2.批量插入客户流失表
         */
        List<Customer> customerList = customerMapper.queryLossCustomerds();
        if (!CollectionUtils.isEmpty(customerList)) {
            // 存流失客户列表
            List<CustomerLoss> customerLossList = new ArrayList<>();

            for (Customer customer : customerList) {
                CustomerLoss customerLoss = new CustomerLoss();
                customerLoss.setCusNo(customer.getKhno());
                customerLoss.setCusName(customer.getName());
                customerLoss.setCusManager(customer.getCusManager());
                customerLoss.setState(0);// 预流失
                customerLoss.setIsValid(1);// 有效
                customerLoss.setCreateDate(new Date());
                customerLoss.setUpdateDate(new Date());
                customerLossList.add(customerLoss);
            }
            AssertUtil.isTrue(customerLossMapper.saveBatch(customerLossList) < customerLossList.size()
                    , CrmConstant.OPS_FAILED_MSG);
            AssertUtil.isTrue(customerMapper.updateCustomerState(customerList) < customerList.size(), CrmConstant.OPS_FAILED_MSG);
        }
    }
}
