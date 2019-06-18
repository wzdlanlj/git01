package xyz.xkun.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.dao.CustomerReprieveMapper;
import xyz.xkun.crm.po.CustomerReprieve;
import xyz.xkun.crm.utils.AssertUtil;

import java.util.Date;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/17 13:15
 */
@Service
public class CustomerReprieveService extends BaseService<CustomerReprieve> {

    @Autowired
    private CustomerReprieveMapper customerReprieveMapper;

    public void saveOrUpdateCustomerReprieve(CustomerReprieve customerReprieve) {
        customerReprieve.setUpdateDate(new Date());

        Integer id = customerReprieve.getId();

        if (null == id) {
            customerReprieve.setIsValid(1);
            customerReprieve.setCreateDate(new Date());
            AssertUtil.isTrue(customerReprieveMapper.save(customerReprieve) < 1, CrmConstant.OPS_FAILED_MSG);
        } else {
            AssertUtil.isTrue(customerReprieveMapper.update(customerReprieve) < 1, CrmConstant.OPS_FAILED_MSG);
        }
    }
}
