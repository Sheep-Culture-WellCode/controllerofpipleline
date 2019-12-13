package com.example.controllerofpipleline.exception;


import com.example.controllerofpipleline.enums.ResultEnum;

public class ResultException extends RuntimeException {
    private Integer code;

    public ResultException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
