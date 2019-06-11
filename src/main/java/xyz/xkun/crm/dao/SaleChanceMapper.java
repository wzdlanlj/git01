package xyz.xkun.crm.dao;

import org.springframework.stereotype.Repository;
import xyz.xkun.crm.base.BaseDao;
import xyz.xkun.crm.po.SaleChance;

import java.util.List;
import java.util.Map;

@Repository
public interface SaleChanceMapper extends BaseDao<SaleChance> {

    public List<Map> queryCustomerManagers();

    public Integer updateSaleChanceDevResult(SaleChance saleChance);
}