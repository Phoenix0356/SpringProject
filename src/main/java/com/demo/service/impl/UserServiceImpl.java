package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.util.DataUtil;
import com.demo.util.JwtTokenUtil;
import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.service.UserService;
import com.demo.vo.ResultBean;
import com.demo.vo.param.UserLoginParam;
import com.demo.vo.param.UserParam;
import com.demo.vo.param.UserPasswordUpdateParam;
import com.demo.vo.param.UserRegisterParam;
import io.swagger.models.auth.In;
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
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    DataUtil<User> dataUtil;

    @Override
    public ResultBean register(UserRegisterParam userRegisterParam){
        String userName=userRegisterParam.getUserName();
        String account=userRegisterParam.getAccount();
        String passWord=userRegisterParam.getPassword();
        String avatar=userRegisterParam.getAvatar();

        if (StringUtils.isBlank(userName)) return ResultBean.error("The name can't be empty!");
        if (StringUtils.isBlank(account)) return ResultBean.error("The account can't be empty!");
        if (StringUtils.isBlank(passWord)) return ResultBean.error("The password can't be empty!");


        User user= userMapper.selectOne(dataUtil.getQueryWrapper("account",account));
        if (user!=null) return ResultBean.error("The account is already exist");
        //insert date
        User newUser=new User();
        newUser.setUsername(userName);
        newUser.setAccount(account);
        newUser.setPassword(encodePassword(passWord));
        newUser.setAvatar(dataUtil.saveAvatar(avatar));
        //insert()返回1表示插入成功，返回0表示插入失败
        return dataUtil.isOperationSuccess(userMapper.insert(newUser),newUser);
    }
    @Override
    public ResultBean login(UserLoginParam userLoginParam){
        String account=userLoginParam.getAccount();
        String password=userLoginParam.getPassword();

        if (StringUtils.isBlank(account)) return ResultBean.error(" The account can't be empty");
        if (StringUtils.isBlank(password)) return ResultBean.error(" The password can't be empty");

        User user=userMapper.selectOne(dataUtil.getQueryWrapper("account",account));

        if (user==null) return ResultBean.error("The user name may be wrong");

        if (matchPassword(password,user.getPassword())) {
            return ResultBean.success("login successfully",
                    jwtTokenUtil.createToken(user));
        }
        else return ResultBean.error("Password is wrong");
    }
    @Override
    public ResultBean getUserByToken(Integer userId){
        User user=userMapper.selectById(userId);
        if (user == null) return ResultBean.error("User not found");
        user.setPassword(null);
        return ResultBean.success("Get information successfully", user);
    }
//    @Override
//    public ResultBean getUserById(int userId){
//        User user=userMapper.selectById(userId);
//        if (user == null) return ResultBean.error("User not found");
//        user.setPassword(null);
//        return ResultBean.success("get information successfully", user);
//    }

    @Override
    public ResultBean updateUserByToken(Integer userId, UserParam userParam) {
        User user=userMapper.selectById(userId);
        user.setUsername(userParam.getUsername());
        user.setAvatar(dataUtil.saveAvatar(userParam.getAvatar()));
        return dataUtil.isOperationSuccess(userMapper.updateById(user),user);
    }
    @Override
    public ResultBean deleteUserByToken(Integer userId){
        return dataUtil.isOperationSuccess(userMapper.deleteById(userId));
    }

    @Override
    public ResultBean updatePassword(Integer userId, UserPasswordUpdateParam userPasswordUpdateParam){
        User user=userMapper.selectById(userId);
        if (matchPassword(userPasswordUpdateParam.getPrePassword(),user.getPassword())){
            user.setPassword(encodePassword(userPasswordUpdateParam.getNewPassword()));
            return dataUtil.isOperationSuccess(userMapper.updateById(user),user);
        }else return ResultBean.error("Wrong password");
    }

    @Override
    public boolean matchPassword(String passwordInput, String passwordOrigin){
        return passwordEncoder.matches(passwordInput,passwordOrigin);
    }
    @Override
    public String encodePassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

}
