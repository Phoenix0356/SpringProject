package com.demo.vo.param;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FavoritesParam {
    @ApiModelProperty("id")
    Integer id;
    @ApiModelProperty("itemName")
    String itemName;
    @ApiModelProperty("introduce")
    String introduce;
    @ApiModelProperty("url")
    String url;
}
