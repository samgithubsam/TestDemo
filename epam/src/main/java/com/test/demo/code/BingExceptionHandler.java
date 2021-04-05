package com.test.demo.code;


import com.test.demo.model.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class BingExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result handleBindException(Exception e) {
        //打印校验住的所有的错误信息
        StringBuilder sb = new StringBuilder("参数错误：[");
        List<ObjectError> list = ((BindException) e).getAllErrors();
        for (ObjectError item : list) {
            sb.append(item.getDefaultMessage()).append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        String msg = sb.toString();
        return new Result().setMessage(msg).setSuccess(false);

    }
}
