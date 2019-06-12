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

//打开关联权限dialog
function openRelationPermissionDialog() {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("来自Crm", "请选择一条数据进行授权");
        return;
    }
    if (rows.length > 1) {
        $.messager.alert("来自Crm", "只能选择一条数据进行授权");
        return;
    }
    //打开弹窗
    $("#permissionDlg").dialog("open");
    //设置roleId
    $("#roleId").val(rows[0].id);

    //加载树
    loadTree(rows[0].id);
}
//发送ajax请求,获取获得的权限
var zTreeObj;

function loadTree(roleId) {
    $.ajax({
        url: ctx + "/module/queryAllModuleByRoleId?roleId=" + roleId,
        type: "post",
        success: function (data) {
            var setting = {
                check: {
                    enable: true,
                    chkboxType: {"Y": "ps", "N": "ps"}
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                }, callback: {
                    onCheck: zTreeOnCheck
                }
            };
            var zNodes = data;
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            zTreeOnCheck();//初始化moduleIds
        }
    })
}

//把勾选的值复制到隐藏域
function zTreeOnCheck() {
    var nodes = zTreeObj.getCheckedNodes();
    var moduleIds = "";
    for (var i = 0; i < nodes.length; i++) {
        moduleIds += "moduleIds=" + nodes[i].id + "&";
    }
    $("#moduleIds").val(moduleIds);
}

//角色勾选
function doGrant() {
    var roleId = $("#roleId").val();
    var moduleIds = $("#moduleIds").val();
    $.ajax({
        url: ctx + "/role/doGrant?roleId=" + roleId + "&" + moduleIds,
        type: "post",
        success: function (data) {
            if (data.code == 200) {
                $.messager.alert("来自Crm", data.msg, "info", function () {
                    //关闭弹窗
                    $("#permissionDlg").dialog("close");
                })
            } else {
                $.messager.alert("来自Crm", data.msg, "error");
            }
        }
    })
}

function closePermissionDlg() {
    // closePermissionDlg("#permissionDlg")
    closeDlgData("permissionDlg")
}
