package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.vo.ResultBean;
import com.demo.vo.param.UserLoginParam;
import com.demo.vo.param.UserRegisterParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Api(tags = "UserController")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @ApiOperation("get user info by id")
    @GetMapping("/user/info")
    public ResultBean getUserInfo(@RequestParam("user_id") int userId){
        return userService.getUserById(userId);
    }
    @ApiOperation("register")
    @PostMapping("/user/register")
    public ResultBean register(@RequestBody UserRegisterParam userRegisterParam){
        return userService.register(userRegisterParam);
    }

    @ApiOperation("Login")
    @PostMapping("/user/login")
    public ResultBean login(@RequestBody UserLoginParam userLoginParam){
            return userService.login(userLoginParam);
    }


}
