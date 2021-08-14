package com.hwangrq.dianping.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hwangrq
 */
@Data
@AllArgsConstructor
public class CommonError {

    /** 错误码 */
    private Integer errCode;

    /** 错误描述 */
    private String errMsg;

    public CommonError(EnumBusinessError enumBusinessError) {
        this.errCode = enumBusinessError.getErrCode();
        this.errMsg = enumBusinessError.getErrMsg();
    }
}
