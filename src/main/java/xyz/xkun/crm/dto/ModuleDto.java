package xyz.xkun.crm.dto;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/11 18:27
 */
public class ModuleDto {
    private Integer id;
    private String name;
    private Integer pId;
    private boolean checked = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
