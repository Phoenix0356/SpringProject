package com.demo.vo.param;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FavoritesParam {
    @ApiModelProperty("favorites_id")
    Integer id;
//    @ApiModelProperty("user_id")
//    Integer userId;
    @ApiModelProperty("itemName")
    String itemName;
    @ApiModelProperty("introduction")
    String introduction;
    @ApiModelProperty("url")
    String url;
}
