package xyz.xkun.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.xkun.crm.base.BaseQuery;

import java.util.Date;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/16 15:54
 */
public class CustomerOrderQuery extends BaseQuery {
    private Integer cusId;

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }
}
