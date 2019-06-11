$(function () {
    $("#dlg").dialog({
        "onClose": function () {
            //触发表单清空
            $("#fm").form("clear")
        }
    })
})
function queryRolesByParams() {
    $("#dg").datagrid("load", {
        roleName: $("#roleName").val(),
        createDate: $("#time").datebox("getValue")
    })
}

//打开保存
function openAddRoleDailog() {
    openAddOrUpdateDlg("dlg", "添加角色")
}

function saveOrUpdateRole() {
    saveOrUpdateData("fm", ctx + "/role/saveOrUpdateRole", "dlg", queryRolesByParams);
}

//打开更新
function openModifyRoleDialog() {
    openModifyDialog("dg", "fm", "dlg", "更新角色");
}

//关闭dialog
function closeDlg() {
    closeDlgData("dlg");
}