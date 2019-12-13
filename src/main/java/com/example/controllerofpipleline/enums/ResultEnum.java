package com.example.controllerofpipleline.enums;

public enum ResultEnum {
    NORMOALSUCCESS(600,"成功"),
    NORMOALERROR(601,"失败"),
    NULLPIPLEIFO(602,"没有此管道的信息")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg){
        this.code =code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
