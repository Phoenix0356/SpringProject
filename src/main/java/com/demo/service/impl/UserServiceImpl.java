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
import com.demo.vo.param.UserRegisterParam;
import io.jsonwebtoken.Claims;
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



    @Override
    public ResultBean register(UserRegisterParam userRegisterParam){
        String userName=userRegisterParam.getUserName();
        String account=userRegisterParam.getAccount();
        String passWord=userRegisterParam.getPassword();
        String avatar=userRegisterParam.getAvatar();

        if (StringUtils.isBlank(userName)) return ResultBean.error("The name can't be empty!");
        if (StringUtils.isBlank(account)) return ResultBean.error("The account can't be empty!");
        if (StringUtils.isBlank(passWord)) return ResultBean.error("The password can't be empty!");
        if (StringUtils.isBlank(avatar)) avatar=DataUtil.saveAvatar(avatar);

        User user= userMapper.selectOne(DataUtil.getQueryWrapper(User.class,"account",account));
        if (user!=null) return ResultBean.error("The account is already exist");
        //insert date
        User newUser=new User();
        newUser.setUsername(userName);
        newUser.setAccount(account);
        newUser.setPassword(new BCryptPasswordEncoder().encode(passWord));
        newUser.setAvatar(userRegisterParam.getAvatar());
        newUser.setAvatar(avatar);

        //insert()返回1表示插入成功，返回0表示插入失败
        if (userMapper.insert(newUser)==1) return ResultBean.success("注册成功",
                JwtTokenUtil.createToken(newUser));
        else return ResultBean.error("register successfully");
    }
    @Override
    public ResultBean login(UserLoginParam userLoginParam){
        String account=userLoginParam.getAccount();
        String password=userLoginParam.getPassword();

        if (StringUtils.isBlank(account)) return ResultBean.error(" The account can't be empty");
        if (StringUtils.isBlank(password)) return ResultBean.error(" The password can't be empty");

        User user=userMapper.selectOne(DataUtil.getQueryWrapper(User.class,
                "account",account));

        if (user==null) return ResultBean.error("The user name may be wrong");

        if (passwordEncoder.matches(password,user.getPassword())) {
            return ResultBean.success("login successfully",
                    JwtTokenUtil.createToken(user));
        }
        else return ResultBean.error("Password is wrong");
    }
    @Override
    public ResultBean getUserByToken(String token){
        Integer userId=JwtTokenUtil.getUserIdFromToken(token);
        User user=userMapper.selectById(userId);
        if (user == null) return ResultBean.error("User not found");
        user.setPassword(null);
        return ResultBean.success("get information successfully", user);
    }
//    @Override
//    public ResultBean getUserById(int userId){
//        User user=userMapper.selectById(userId);
//        if (user == null) return ResultBean.error("User not found");
//        user.setPassword(null);
//        return ResultBean.success("get information successfully", user);
//    }

    @Override
    public ResultBean updateUserByToken(String token,UserParam userParam) {
        User user=userMapper.selectById(JwtTokenUtil.getUserIdFromToken(token));
        if (!jwtTokenUtil.isValidateToken(token)){
            return ResultBean.error("please login again");
        }

        user.setUsername(userParam.getUsername());
        user.setAvatar(DataUtil.saveAvatar(userParam.getAvatar()));

        this.updateById(user);
        return ResultBean.success("information update successfully",user);
    }
    @Override
    public ResultBean deleteUserByToken(String token){
        int result=userMapper.deleteById(JwtTokenUtil.getUserIdFromToken(token));
        return result>0?ResultBean.success("Account delete successfully"):ResultBean.error("Account delete unsuccessfully");
    }
}
