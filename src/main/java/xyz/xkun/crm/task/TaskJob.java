package xyz.xkun.crm.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.xkun.crm.service.CustomerService;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/17 9:16
 */
//@Component
public class TaskJob {

    @Autowired
    private CustomerService customerService;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void job01(){
        System.out.println("=====================start=====================");
        customerService.addLossCustomerds();
        System.out.println("=====================Over=====================");
    }
}
