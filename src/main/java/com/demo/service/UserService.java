package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.User;
import com.demo.vo.ResultBean;
import com.demo.vo.param.UserLoginParam;
import com.demo.vo.param.UserParam;
import com.demo.vo.param.UserPasswordUpdateParam;
import com.demo.vo.param.UserRegisterParam;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {
    ResultBean register(UserRegisterParam userRegisterParam);
    ResultBean login(UserLoginParam userLoginParam);
    ResultBean getUserByToken(Integer userId);
//    ResultBean getUserById(int userId);

    ResultBean updateUserByToken(Integer userId, UserParam userParam);

    ResultBean deleteUserByToken(Integer userId);

    ResultBean updatePassword(Integer userId, UserPasswordUpdateParam userPasswordUpdateParam);

    boolean matchPassword(String passwordInput, String passwordOrigin);

    String encodePassword(String password);
}
