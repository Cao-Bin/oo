package com.cb.users.api.vo;

public class ErrorResponseVO {
    private String msg;
    private String code;

    public ErrorResponseVO(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public ErrorResponseVO() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
