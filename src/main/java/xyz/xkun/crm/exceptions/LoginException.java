package xyz.xkun.crm.exceptions;


import xyz.xkun.crm.constants.CrmConstant;

/**
 * Created by lp on 2018/1/3.
 */
public class LoginException extends RuntimeException {
    private Integer code = CrmConstant.USER_NOT_LOGIN_CODE;
    private String msg = CrmConstant.USER_NOT_LOGIN_MSG;


    public LoginException(Integer code) {
        this.code = code;
    }

    public LoginException(String msg) {
        this.msg = msg;
    }

    public LoginException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
