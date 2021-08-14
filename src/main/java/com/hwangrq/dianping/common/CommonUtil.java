package com.hwangrq.dianping.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author hwangrq
 */
public class CommonUtil {

    public static String processErrorString(BindingResult bindingResult){
        // 绑定结果无错误，返回空字符串
        if(!bindingResult.hasErrors()){
            return "";
        }
        // 有错误的话拼接errMsg
        StringBuilder stringBuilder = new StringBuilder();
        for(FieldError fieldError:bindingResult.getFieldErrors()){
            stringBuilder.append(fieldError.getDefaultMessage()).append(",");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
}
