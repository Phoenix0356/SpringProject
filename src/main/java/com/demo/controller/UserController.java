package com.demo.controller;

import com.demo.service.UserService;
import com.demo.vo.ResultBean;
import com.demo.vo.param.UserLoginParam;
import com.demo.vo.param.UserParam;
import com.demo.vo.param.UserPasswordUpdateParam;
import com.demo.vo.param.UserRegisterParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@Api(tags = "UserController")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @ApiOperation("get user info by token")
    @GetMapping("/user/home")
    public ResultBean getUserInfoById(@RequestHeader("userId") Integer userId){
        //for request header
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            return userService.getUserByToken(token);
//        }
//        return ResultBean.error("fetch information failed");

        return userService.getUserByToken(userId);
    }

//    @ApiOperation("get user info")
//    @GetMapping("/user/home")
//    public ResultBean getUserInfo(@RequestParam(required = false) Integer userId, @RequestParam(required = false) String token){
//        if (userId != null) {
//            return userService.getUserById(userId);
//        } else if (token != null) {
//            return userService.getUserByToken(token);
//        } else {
//            return ResultBean.error("No valid parameters provided");
//        }
//    }

    @ApiOperation("update user info")
    @PutMapping("/user/update")
    public ResultBean updateUserInfoById(@RequestHeader("userId") Integer userId, @RequestBody UserParam userParam){
        return userService.updateUserByToken(userId, userParam);
    }
    @ApiOperation("update user password")
    @PutMapping("/user/password/update")
    public ResultBean updateUserPassword(@RequestHeader("userId") Integer userId, @RequestBody UserPasswordUpdateParam userPasswordUpdateParam){
        return userService.updatePassword(userId,userPasswordUpdateParam);
    }

    @ApiOperation("delete user account")
    @DeleteMapping("/user/delete")
    public ResultBean deleteUserByToken(@RequestHeader("userId") Integer userId){
        return userService.deleteUserByToken(userId);
    }

    @ApiOperation("register")
    @PostMapping("/user/register")
    public ResultBean register(@RequestBody UserRegisterParam userRegisterParam){
        return userService.register(userRegisterParam);
    }

    @ApiOperation("Login")
    @PostMapping("/user/login")
    public ResultBean login(@RequestBody UserLoginParam userLoginParam){return userService.login(userLoginParam);}

    @ApiOperation("logout")
    @PostMapping("/user/logout")
    public ResultBean logout(){
        return ResultBean.success("exit");
    }


}
