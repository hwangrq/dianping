package com.hwangrq.dianping.common;

import lombok.Data;

/**
 * @author hwangrq
 */
@Data
public class CommonRes {

    /** 表明对应请求的返回处理结果，“success”或"fail" */
    private String status;

    /**
     *  status=success -> json类型数据
     *  status=fail -> 通用错误码对应格式
     */
    private Object data;

    public static CommonRes create(Object result) {
        return CommonRes.create(result, "success");
    }

    public static CommonRes create(Object result, String status) {
        CommonRes commonRes = new CommonRes();
        commonRes.setData(result);
        commonRes.setStatus(status);
        return commonRes;
    }
}
