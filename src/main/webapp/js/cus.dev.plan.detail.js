$(function () {
    var sid = $("#saleChanceId").val();
    $('#dg').edatagrid({
        url: ctx + "/cusDevPlan/queryCusDevPlansByParams?sid=" + sid,
        saveUrl: ctx + "/cusDevPlan/saveOrUpdateCusDevPlan?sid=" + sid,
        updateUrl: ctx + "/cusDevPlan/saveOrUpdateCusDevPlan?sid=" + sid
    });

    //初始判断:如果开发状态devResult未2和3就要隐藏工具条不可编辑
    var devResult = $("#devResult").val();
    if (devResult == 2 || devResult == 3) {
        //隐藏工具条
        $("#toolbar").hide();
        //使表格不可编辑
        $("#dg").edatagrid("disableEditing");
    }
});

//添加
function addRow() {
    $("#dg").edatagrid("addRow");
}

function saveOrUpdateCusDevPlan() {
    $("#dg").edatagrid("saveRow");
    var sid = $("#saleChanceId").val();
    if (1 == $(".datagrid-cell-rownumber").length) {
        $.ajax({
            url: ctx + "/saleChance/updateSaleChanceDevResult",
            type: "post",
            data: {
                id: sid,
                devResult: 1
            }, success: function (data) {
                if (data.code == 200) {
                    $("#dg").edatagrid("load");
                }
            }
        });
    } else {
        setTimeout(function () {
            $("#dg").edatagrid("load");
        },80);

    }
}

//删除
function delCusDevPlan() {
    deleteData("dg", ctx + "/cusDevPlan/deleteCusDevPlanBatch", loadEdatagrid);
}

function loadEdatagrid() {
    $("#dg").edatagrid("load");
}

//更新开发状态
function updateSaleChanceDevResult(state) {
    var sid = $("#saleChanceId").val();
    $.ajax({
        url: ctx + "/saleChance/updateSaleChanceDevResult",
        type: "post",
        data: {
            id: sid,
            devResult: state
        }, success: function (data) {
            if (data.code == 200) {
                $.messager.alert("来自Crm", data.msg, "info", function () {
                    //隐藏工具条
                    $("#toolbar").hide();
                    //使表格不可编辑
                    $("#dg").edatagrid("disableEditing");
                });
            } else {
                $.messager.alert("来自Crm", data.msg, "error");
            }
        }
    })
}

















