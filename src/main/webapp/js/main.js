function openTab(text, url, iconCls) {
    if ($("#tabs").tabs("exists", text)) {
        $("#tabs").tabs("select", text);
    } else {
        var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add", {
            title: text,
            iconCls: iconCls,
            closable: true,
            content: content
        });
    }
}

function logout() {
    $.messager.confirm("来自crm的消息", "您确认想退出系统吗?", function (TF) {
        if (TF) {
            $.removeCookie("realName");
            $.removeCookie("userIdStr");
            $.removeCookie("userName");
            window.location.href = ctx + "/index";
        }
    })
}

function openPasswordModifyDialog() {
    $("#dlg").dialog("open");
}
function closePasswordModifyDialog() {
    $("#dlg").dialog("close");
}


function modifyPassword() {
    $("#fm").form("submit", {
        url: ctx + '/user/updateUserPwd',
        onSubmit: function () {
            return $(this).form("validate");
        }, success: function (data) {
            //手动解析JSON
            // console.log(data);
            data = JSON.parse(data);
            if (data.code == 200) {
                $.messager.alert("来自crm", data.msg, "info", function () {
                    $.removeCookie("realName");
                    $.removeCookie("userIdStr");
                    $.removeCookie("userName");
                    window.location.href = ctx + "/index";
                });
            } else {
                $.messager.alert("来自crm", data.msg, "error");
            }
        }
    })
}