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
    @TableId(value = "id",type = IdType.AUTO)
    Integer id;
    @TableField("item_name")
    String itemName;
    @TableField("introduce")
    String introduce;
    @TableField("url")
    String url;

}
