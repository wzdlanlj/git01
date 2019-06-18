//格式化1/0
function formateState(val) {
    switch (val) {
        case 0:
            return "暂缓流失";
        case 1:
            return "确认流失";
    }
}

//格式化内容
function formateOp(val, row) {
    //查看详情或者确认流失
    var state = row.state;
    if (state == 0) {
        var href = "javascript:openAddReprieveDataTab(" + row.id + ")";
        return "<a href=" + href + ">添加暂缓</a>";
    } else {
        return "确认流失";
    }
}

//搜索
function queryCustomerLossByParams() {
    $("#dg").datagrid("load", {
        cusNo: $("#cusNo").val(),
        cusName: $("#cusName").val(),
        createDate: $("#time").datebox("getValue")
    });
}

//打开添加暂缓
function openAddReprieveDataTab(lossId) {
    window.parent.openTab("添加展缓措施" + lossId, ctx + "/customerReprieve/index?lossId=" + lossId);
}