package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.User;
import com.demo.vo.ResultBean;
import com.demo.vo.param.UserLoginParam;
import com.demo.vo.param.UserRegisterParam;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {
    public ResultBean getUserById(int userId);

    ResultBean register(UserRegisterParam userRegisterParam);

    ResultBean login(UserLoginParam userLoginParam);
}
