package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
@TableName("user")
public class User {
    @TableId(value = "user_Id", type = IdType.AUTO)
    private Integer userId;

    @TableField("account")
    private String account;

    @TableField("user_name")
    private String userName;

    @TableField("pass_word")
    private String password;

    @TableField("avatar")
    private String avatar;

//    public String toString(int userId){
//        return String.valueOf(userId);
//    }
}
