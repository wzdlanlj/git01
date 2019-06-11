package xyz.xkun.crm.query;

import xyz.xkun.crm.base.BaseQuery;

import java.util.Date;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/5 20:51
 */

public class SaleChanceQuery extends BaseQuery {
    private String customerName;//客户名称

    private Integer state;//开发状态

    private Integer devResult;//开发结果

    private String createDate; //创建时间

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDevResult() {
        return devResult;
    }

    public void setDevResult(Integer devResult) {
        this.devResult = devResult;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SaleChanceQuery{" +
                "customerName='" + customerName + '\'' +
                ", state=" + state +
                ", devResult=" + devResult +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
