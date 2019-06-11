package xyz.xkun.crm.query;

import xyz.xkun.crm.base.BaseQuery;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/11 10:05
 */
public class RoleQuery extends BaseQuery {
    private String roleName;
    private String createDate;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
