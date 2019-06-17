//格式化字符串
function formateState(val) {
    switch (val) {
        case 0:
            return "未支付"
        case 1:
            return "已支付"
        default:
            return "状态错误"
    }
}

//查看详情
function formateOp(val, row) {
    var orderId = row.id;
    var href = "javascript:openOrderDeatilDialog(" + orderId + ")";
    return "<a href = " + href + ">查看详情</a>";
}

//打开查看详情页
function openOrderDeatilDialog(orderId) {
    var rows = $("#dg").datagrid("getSelections");
    //打开窗口
    $("#dlg").dialog("open");
    //回显表单数据
    $("#fm").form("load", rows[0]);

    // 回显订单详情
    $("#dg2").datagrid({
        url: ctx + "/orderDetails/queryOrderDetailsByParams?orderId=" + rows[0].id
    })
}












