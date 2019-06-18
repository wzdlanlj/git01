package xyz.xkun.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.dao.CustomerServeMapper;
import xyz.xkun.crm.dao.UserMapper;
import xyz.xkun.crm.dto.UserDto;
import xyz.xkun.crm.po.CustomerServe;
import xyz.xkun.crm.utils.AssertUtil;

import java.util.Date;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/17 19:41
 */
@Service
public class CustomerServeService extends BaseService<CustomerServe> {

    @Autowired
    private CustomerServeMapper customerServeMapper;

    @Autowired
    private UserMapper userMapper;

    public void saveOrUpdateCustomerServe(CustomerServe customerServe, Integer userId) {
        customerServe.setUpdateDate(new Date());
        Integer id = customerServe.getId();
        UserDto userDto = userMapper.queryById(userId);
        if (null == id) {
            customerServe.setCreatePeople(userDto.getUserName());
            customerServe.setState("1");
            customerServe.setIsValid(1);
            customerServe.setCreateDate(new Date());

            AssertUtil.isTrue(customerServeMapper.save(customerServe) < 1, CrmConstant.OPS_FAILED_MSG);
        } else {
            /**
             * state变化
             */
            String state = customerServe.getState();
            if (state.equals("1")){
                customerServe.setState("2");
                customerServe.setAssignTime(new Date());//分配时间
            }else if (state.equals("2")){
                customerServe.setState("3");
                customerServe.setServiceProceTime(new Date());//处理时间
                customerServe.setServiceProcePeople(userDto.getUserName());//处理人
            }else if (state.equals("3")){
                customerServe.setState("4");
            }
            AssertUtil.isTrue(customerServeMapper.update(customerServe)<1,CrmConstant.OPS_FAILED_MSG);
        }
    }
}
