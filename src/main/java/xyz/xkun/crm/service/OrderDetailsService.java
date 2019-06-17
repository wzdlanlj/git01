package xyz.xkun.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.dao.OrderDetailsMapper;
import xyz.xkun.crm.po.OrderDetails;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/16 17:33
 */
@Service
public class OrderDetailsService extends BaseService<OrderDetails> {

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
}
