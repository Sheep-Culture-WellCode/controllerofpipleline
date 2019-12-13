package com.example.controllerofpipleline.handle;


import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.exception.ResultException;
import com.example.controllerofpipleline.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.ParseException;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof ResultException){
            ResultException resultException = (ResultException) e;
            return ResultUtil.error(resultException.getCode(),resultException.getMessage());
        }else if (e instanceof ParseException){
            return ResultUtil.error(200,e.getMessage());
        }else {
            return ResultUtil.error(-1,e.getMessage()+"（出现了未知错误：请联系后端开发人员杨文井-）");
        }
    }
}
