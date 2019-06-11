function login() {
    var userName = $("#userName").val();
    var userPwd = $("#userPwd").val();

    if (isEmpty(userName)) {
        $.confirm({
            title: '错误',
            content: '用户名不能为空',
            type: 'orange',
            buttons: {
                ok: {
                    text: '确认'
                }
            }
        });
        return;
    }
    if (isEmpty(userPwd)) {
        $.confirm({
            title: '错误',
            content: '密码不能为空',
            type: 'green',
            buttons: {
                ok: {
                    text: '确认'
                }
            }
        });
        return;
    }

    $.ajax({
        url: ctx + "/user/login",
        type: "post",
        data: {
            userName: userName,
            userPwd: userPwd
        }, success: function (data) {
            if (data.code == 200) {
                $.cookie("userIdStr", data.result.userIdStr);
                $.cookie("userName", data.result.userName);
                $.cookie("realName", data.result.realName);
                window.location.href = ctx + "/main";
            } else {
                $.confirm({
                    title: '登录失败',
                    content: data.msg,
                    type: 'red',
                    buttons: {
                        ok: {
                            text: '确认'
                        }
                    }
                });
            }
        }
    })
}