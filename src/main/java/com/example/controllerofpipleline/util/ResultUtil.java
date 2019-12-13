package com.example.controllerofpipleline.util;


import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.enums.ResultEnum;

public class ResultUtil {
    public static Result success(Object o){
        Result result = new Result(ResultEnum.NORMOALSUCCESS,o);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static Result error(ResultEnum resultEnum){
        Result resultX = new Result();
        resultX.setCode(resultEnum.getCode());
        resultX.setMsg(resultEnum.getMsg());
        return resultX;
    }

    public static Result error(){
        Result result = new Result(ResultEnum.NORMOALERROR,null);
        return result;
    }
}
