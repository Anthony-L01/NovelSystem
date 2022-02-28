package com.lxc.novelsystem.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description:
 * @author: Anthony
 * @time: 2022/1/25
 */

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("用户")
    public class User {
        @ApiModelProperty("用户手机号")
        private String phoneNumber;
        @ApiModelProperty("用户名")
        private String name;
        @ApiModelProperty("用户密码")
        private String password;
        @ApiModelProperty("用户账号")
        private String account;


}
