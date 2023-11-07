package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("favorites")
public class Favorites {
    @TableId(value = "fav_id",type = IdType.AUTO)
    Integer favId;
    @TableField("item_name")
    String itemName;
    @TableField("introduction")
    String introduction;
    @TableField("url")
    String url;

}
