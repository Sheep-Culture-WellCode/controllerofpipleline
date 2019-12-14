package com.example.controllerofpipleline.enums;

public enum ResultEnum {
    NORMOALSUCCESS(600,"成功"),
    NORMOALERROR(601,"失败"),
    NULLPIPLEIFO(602,"没有此管道的信息"),
    ERROFSELE(603,"没有桩号信息"),
    ERROARGUMENT(604,"输入参数有误"),
    ERROMYSQL(605,"数据库发生了错误，请保证每次添加规则为：首先创建相应的管道信息，才能使用管道风险")
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
