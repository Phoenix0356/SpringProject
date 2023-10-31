package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Util.DataUtil;
import com.demo.Util.JwtTokenUtil;
import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.service.UserService;
import com.demo.vo.ResultBean;
import com.demo.vo.param.UserLoginParam;
import com.demo.vo.param.UserRegisterParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    DataUtil dataUtil;
    @Override
    public ResultBean getUserById(int userId){
        User user=userMapper.selectById(userId);

        if (user == null) return ResultBean.error("User not found");

        user.setPassword(null);
        return ResultBean.success("get information suceessfully", user);
    }

    @Override
    public ResultBean register(UserRegisterParam userRegisterParam){
        String userName=userRegisterParam.getUserName();
        String passWord=userRegisterParam.getPassword();

        if (StringUtils.isBlank(userName)) return ResultBean.error("The name can't be empty!");
        if (StringUtils.isBlank(passWord)) return ResultBean.error("The password can't be empty!");

        User user= userMapper.selectOne(dataUtil.getQuerWrapper(User.class,"user_name",userName));
        if (user!=null) return ResultBean.error("The name has already been taken");
        //insert date
        User newUser=new User();
        newUser.setUserName(userName);
        newUser.setPassword(new BCryptPasswordEncoder().encode(passWord));

        //insert()返回1表示插入成功，返回0表示插入失败
        if (userMapper.insert(newUser)==1) return ResultBean.success("注册成功",newUser);
        else return ResultBean.error("注册失败");
    }
    @Override
    public ResultBean login(UserLoginParam userLoginParam){
        String userName=userLoginParam.getUserName();
        String password=userLoginParam.getPassword();
        if (StringUtils.isBlank(userName)) return ResultBean.error(" The user name can't be empty");
        if (StringUtils.isBlank(password)) return ResultBean.error(" The password can't be empty");

        User user=userMapper.selectOne(dataUtil.getQuerWrapper(User.class,"user_name",userName));

        if (user==null) return ResultBean.error("The user name may be wrong");

        if (passwordEncoder.matches(password,user.getPassword())) {
            return ResultBean.success("Login successfully", jwtTokenUtil.createToken(user));
        }
        else return ResultBean.error("Password is wrong");
    }
}
