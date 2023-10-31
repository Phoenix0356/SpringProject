package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Util.DataUtil;
import com.demo.Util.JwtTokenUtil;
import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.service.UserService;
import com.demo.vo.LoginBean;
import com.demo.vo.ResultBean;
import com.demo.vo.param.UserLoginParam;
import com.demo.vo.param.UserParam;
import com.demo.vo.param.UserRegisterParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private DataUtil dataUtil;

    @Override
    public ResultBean register(UserRegisterParam userRegisterParam){
        String userName=userRegisterParam.getUserName();
        String account=userRegisterParam.getAccount();
        String passWord=userRegisterParam.getPassword();
        String avatar=userRegisterParam.getAvatar();


        if (StringUtils.isBlank(userName)) return ResultBean.error("The name can't be empty!");
        if (StringUtils.isBlank(account)) return ResultBean.error("The account can't be empty!");
        if (StringUtils.isBlank(passWord)) return ResultBean.error("The password can't be empty!");
        if (StringUtils.isBlank(avatar)) avatar=dataUtil.saveAvatar(avatar);

        User user= userMapper.selectOne(dataUtil.getQueryWrapper(User.class,"account",account));
        if (user!=null) return ResultBean.error("The account is already exist");
        //insert date
        User newUser=new User();
        newUser.setUserName(userName);
        newUser.setAccount(account);
        newUser.setPassword(new BCryptPasswordEncoder().encode(passWord));
        newUser.setAvatar(userRegisterParam.getAvatar());
        newUser.setAvatar(avatar);

        //insert()返回1表示插入成功，返回0表示插入失败
        if (userMapper.insert(newUser)==1) return ResultBean.success("注册成功",
                new LoginBean(jwtTokenUtil.createToken(newUser.getUserId().toString()),
                        newUser.getUserId()));
        else return ResultBean.error("register successfully");
    }
    @Override
    public ResultBean login(UserLoginParam userLoginParam){
        String account=userLoginParam.getAccount();
        String password=userLoginParam.getPassword();

        if (StringUtils.isBlank(account)) return ResultBean.error(" The account can't be empty");
        if (StringUtils.isBlank(password)) return ResultBean.error(" The password can't be empty");

        User user=userMapper.selectOne(dataUtil.getQueryWrapper(User.class,
                "account",account));

        if (user==null) return ResultBean.error("The user name may be wrong");

        if (passwordEncoder.matches(password,user.getPassword())) {
            String userId=user.getUserId().toString();
            return ResultBean.success("Login successfully",
                    new LoginBean(jwtTokenUtil.createToken(user.getUserId().toString()),
                            user.getUserId()));
        }
        else return ResultBean.error("Password is wrong");
    }
    @Override
    public ResultBean getUserByToken(String token){
        String userId=jwtTokenUtil.getUserIdFromToken(token);
        User user=userMapper.selectById(userId);
        if (user == null) return ResultBean.error("User not found");
        user.setPassword(null);
        return ResultBean.success("get information successfully", user);
    }
    @Override
    public ResultBean getUserById(int userId){
        User user=userMapper.selectById(userId);
        if (user == null) return ResultBean.error("User not found");
        user.setPassword(null);
        return ResultBean.success("get information successfully", user);
    }

    @Override
    public ResultBean updateUserById(UserParam userParam) {
        User user=userMapper.selectById(userParam.getUserId());

        user.setUserName(userParam.getUsername());
        user.setAvatar(dataUtil.saveAvatar(userParam.getAvatar()));

        this.updateById(user);
        return ResultBean.success("information update successfully",user);
    }


}
