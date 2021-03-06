package com.hwangrq.dianping.common;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用异常处理器
 * @author hwangrq
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonRes doError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        if (ex instanceof BusinessException) {
            return CommonRes.create(((BusinessException) ex).getCommonError(), "fail");
        } else if (ex instanceof NoHandlerFoundException) {
            CommonError commonError = new CommonError(EnumBusinessError.NO_HANDLER_FOUND);
            return CommonRes.create(commonError, "fail");
        } else if(ex instanceof ServletRequestBindingException){
            CommonError commonError = new CommonError(EnumBusinessError.BIND_EXCEPTION_ERROR);
            return CommonRes.create(commonError,"fail");
        } else {
            CommonError commonError = new CommonError(EnumBusinessError.UNKNOWN_ERROR);
            return CommonRes.create(commonError,"fail");
        }
    }
}
