package xyz.xkun.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.dao.CustomerOrderMapper;
import xyz.xkun.crm.po.CustomerOrder;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/16 16:26
 */
@Service
public class CustomerOrderService extends BaseService<CustomerOrder> {

    @Autowired
    private CustomerOrderMapper customerOrderMapper;
}
