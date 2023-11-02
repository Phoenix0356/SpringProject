package com.demo.vo.param;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class UserPasswordUpdateParam {
    @ApiModelProperty("修改前密码")
    String prePassword;
    @ApiModelProperty("修改后密码")
    String newPassword;

}
