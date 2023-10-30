package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class test {

    @Autowired
    private UserService userService;
    @GetMapping("/test")
    public List<User> test2(){
        List<User> userList=userService.list();
        for (User user:userList) user.setPassword(null);
        return userList;
    }
}
