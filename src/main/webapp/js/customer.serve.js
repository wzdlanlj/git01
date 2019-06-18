//保存服务
function saveCustomerServe() {
    $("#fm").form("submit", {
        url: ctx + "/customerServe/saveOrUpdateCustomerServe",
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
                    $("#fm").form("clear");
                });
            } else {
                $.messager.alert("来自Crm", data.msg, "error");
            }
        }
    })
}

function resetValue() {
    $("#fm").form("clear");

}

//分配弹窗
function openCustomerServeAssignDialog() {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("来自Crm", "请选择一条数据");
        return;
    }
    if (rows.length > 1) {
        $.messager.alert("来自Crm", "只能选择一条数据");
        return;
    }
    $("#fm").form("load", rows[0]);
    openAddOrUpdateDlg("dlg", "操作");
}

//分配添加或者更新
function addCustomerServeAssign() {
    saveOrUpdateData("fm", ctx + "/customerServe/saveOrUpdateCustomerServe", "dlg", function () {
        $("#dg").datagrid("load");
    })
}

//打开处理
function openCustomerServeProceDialog() {
    openCustomerServeAssignDialog();
}

//处理操作
function addCustomerServeProce() {
    addCustomerServeAssign();
}

//打开反馈
function openCustomerServeFeedBackDialog() {
    openCustomerServeAssignDialog();
}

//处理反馈
function addCustomerServeFeedBack() {
    addCustomerServeAssign();
}

//搜索
function queryCustomerServesByParams() {
    $("#dg").datagrid("load", {
        customer: $("#cusName").val(),
        myd: $("#myd").combobox("getValue"),
        createDate: $("#time").datebox("getValue")
    });
}

