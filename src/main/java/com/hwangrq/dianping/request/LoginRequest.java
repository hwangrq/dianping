package com.hwangrq.dianping.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hwangrq
 */
@Data
public class LoginRequest {

    @NotBlank(message = "手机号不能为空")
    private String telephone;

    @NotBlank(message = "密码不能为空")
    private String password;
}
