package xyz.xkun.crm.query;

import xyz.xkun.crm.base.BaseQuery;
import xyz.xkun.crm.po.Customer;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/13 11:02
 */
public class CustomerQuery extends BaseQuery {

    private String khno;

    private String name;

    private String fr;


    public String getKhno() {
        return khno;
    }

    public void setKhno(String khno) {
        this.khno = khno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    @Override
    public String toString() {
        return "CustomerQuery{" +
                "khno='" + khno + '\'' +
                ", name='" + name + '\'' +
                ", fr='" + fr + '\'' +
                '}';
    }
}
