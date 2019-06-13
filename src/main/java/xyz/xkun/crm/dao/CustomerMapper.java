package xyz.xkun.crm.dao;

import org.springframework.stereotype.Repository;
import xyz.xkun.crm.base.BaseDao;
import xyz.xkun.crm.po.Customer;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerMapper extends BaseDao<Customer> {

    public List<Map> queryDataDicsByDicName(String dicName);

    public Integer deleteCustomer(Integer id);
}