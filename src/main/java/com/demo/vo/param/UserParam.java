package com.demo.vo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.RequestParam;
@Data
@Accessors(chain = true)
public class UserParam {
    @ApiModelProperty("用户ID")
    int userId;
    @ApiModelProperty("用户名")
    String username;
    @ApiModelProperty("头像")
    String avatar;

}
