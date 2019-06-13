$(function () {
    $("#dlg").dialog({
        "onClose": function () {
            //触发表单清空
            $("#fm").form("clear")
        }
    })
})

function queryCustomersByParams() {
    $("#dg").datagrid("load", {
        khno: $("#khno").val(),
        name: $("#cusName").val(),
        fr: $("#fr").val()
    })
}

//打开创建客户页面
function openAddCustomerDialog() {
    openAddOrUpdateDlg("dlg", "创建客户")
}

//点击保存
function saveOrUpdateCustomer() {
    saveOrUpdateData("fm", ctx + "/customer/saveOrUpdateCustomer", "dlg", queryCustomersByParams);
}

//修改
function openModifyCustomerDialog() {
    openModifyDialog("dg", "fm", "dlg", "客户更新");
}

//关闭dialog
function closeCustomerDialog() {
    closeDlgData("dlg");
}

//删除
function deleteCustomer() {
    deleteData("dg", ctx + "/customer/deleteCustomer", queryCustomersByParams);
}

//打开历史订单详情
function openCustomerOtherInfo(name, type) {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("来自Crm", "请选择一条数据");
        return;
    }
    if (rows.length > 1) {
        $.messager.alert("来自Crm", "只能选择一条数据");
        return;
    }

    var cusId = rows[0].id;
    if (type == 3) {
        window.parent.openTab(name+'_'+cusId,ctx+"/customerOrder/index?cusId="+cusId);
    }
}
