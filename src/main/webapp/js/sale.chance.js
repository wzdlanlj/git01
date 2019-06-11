function querySaleChancesByParams() {
    $("#dg").datagrid('load', {
        customerName: $("#customerName").val(),
        state: $("#state").combobox('getValue'),
        devResult: $("#devResult").combobox('getValue'),
        createDate: $("#time").combobox('getValue')
    })
}

// 数据格式化显示
function formatterState(value, row, index) {
    if (0 == value) {
        return "未分配";
    }
    if (1 == value) {
        return "已分配";
    }
}

function formatterDevResult(value, row, index) {
    if (0 == value) {
        return "未开发";
    } else if (1 == value) {
        return "开发中";
    } else if (2 == value) {
        return "开发成功";
    } else if (3 == value) {
        return "开发失败";
    }
}

//修改背景色
$(function () {
    //在页面加载完成后触发
    console.log($("#dg"));
    $("#dg").datagrid({
        rowStyler: function (index, row) {
            if (row.devResult == 0) {
                return "background-color:#5bc0de;"; // 蓝色
            } else if (row.devResult == 1) {
                return "background-color:#f0ad4e;"; // 黄色
            } else if (row.devResult == 2) {
                return "background-color:#5cb85c;"; // 绿色
            } else if (row.devResult == 3) {
                return "background-color:#d9534f;"; // 红色
            }

        }
    })
})

//打开添加窗口
function openAddSaleChacneDialog() {
    $("#dlg").dialog("open").dialog("setTitle", "添加营销机会");
   /* $("#id").val("");
    $("#fm").form('clear');*/
}

function saveOrUpdateSaleChance() {
    $("#fm").form("submit", {
        url: ctx + "/saleChance/saveOrUpdateSaleChance",
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.code == 200) {
                $.messager.alert("来自Crm", data.msg, "info", function () {
                    //关闭弹窗
                    $("#dlg").dialog("close");
                    //刷新数据表格
                    $("#dg").datagrid("load");
                });
            } else {
                $.messager.alert("来自Crm", data.msg, "error");
            }
        }
    })
}

//关闭添加窗口
function closeDlg() {
    $("#dlg").dialog("close");
    $("#id").val("");
    $("#fm").form('clear');
}

//更新判断
function openModifySaleChanceDialog() {
    //1.判断是否被选中或者多选
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length < 1) {
        $.messager.alert("来自crm", "请选择一条记录进行更新", "info");
        return;
    }
    if (rows.length > 1) {
        $.messager.alert("来自crm", "最多只能选择一条进行更新", "info");
        return;
    }
    $("#fm").form("load", rows[0]);
    $("#dlg").dialog("open").dialog("setTitle", "更新营销机会");
}


//批量删除
function deleteSaleChance() {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length <= 0) {
        $.messager.alert("来自Crm", "请选择一条数据进行删除");
        return;
    }
    $.messager.confirm("来自Crm", "确定删除" + rows.length + "条数据?", function (r) {
        if (r) {
            var ids = "";
            for (var i = 0; i < rows.length; i++) {
                if (i==rows.length-1){
                    ids += "ids=" + rows[i].id;
                    break;
                }
                ids += "ids=" + rows[i].id + "&";
            }
            $.ajax({
                url: ctx + "/saleChance/deleteSaleChanceBatch?" + ids,
                type: "post",
                success: function (data) {
                    if (data.code == 200) {
                        $.messager.alert("来自Crm", data.msg, "info", function () {
                            //关闭弹窗
                            $("#dlg").dialog("close");
                            //刷新数据表格
                            $("#dg").datagrid("load");
                        });
                    } else {
                        $.messager.alert("来自Crm", data.msg, "error");
                    }
                }
            })

        }
    })
}














