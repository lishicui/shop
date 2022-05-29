package com.shopping.service.impl;

import com.shopping.eneity.User;
import com.shopping.mapper.UserMapper;
import com.shopping.result.ResultInfo;
import com.shopping.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LSC
 * @date 2022/3/3
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultInfo regist(User user) {
        User userByUserName = userMapper.getUserByUserName(user.getName());
        if (userByUserName!=null){
            return ResultInfo.genErrorResultInfo("用户名已存在",10001);
        }
        int insert = userMapper.insert(user);
        if (insert>0){
            return ResultInfo.genSuccessResultInfo(null,"注册成功",10000);
        }else{
            return ResultInfo.genErrorResultInfo("注册失败",10001);
        }
    }

    @Override
    public ResultInfo login(String name, String pwd) {
        User user = userMapper.getUser(name, pwd);
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setAddress(user.getAddress());
        user1.setPhone(user.getPhone());
        list.add(user1);
        if (user==null){
            return ResultInfo.genErrorResultInfo("账号或密码错误",10001);
        }else{
            return ResultInfo.genSuccessResultInfo(user1,"登陆成功",10000);
        }
    }
}
