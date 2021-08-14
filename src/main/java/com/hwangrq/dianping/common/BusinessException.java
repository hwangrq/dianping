package com.hwangrq.dianping.common;

/**
 * @author hwangrq
 */
public class BusinessException extends Exception {

    private CommonError commonError;

    public BusinessException(EnumBusinessError emBusinessError) {
        super();
        this.commonError = new CommonError(emBusinessError);
    }

    public BusinessException(EnumBusinessError emBusinessError, String errMsg){
        super();
        this.commonError = new CommonError(emBusinessError);
        this.commonError.setErrMsg(errMsg);
    }

    public CommonError getCommonError() {
        return commonError;
    }

    public void setCommonError(CommonError commonError) {
        this.commonError = commonError;
    }
}
