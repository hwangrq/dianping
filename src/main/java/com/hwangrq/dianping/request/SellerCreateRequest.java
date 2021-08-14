package com.hwangrq.dianping.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hwangrq
 */
@Data
public class SellerCreateRequest {

    @NotBlank(message = "商户名不能为空")
    private String name;
}
