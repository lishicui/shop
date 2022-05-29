package com.shopping.controller;

import com.shopping.eneity.User;
import com.shopping.result.ResultInfo;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LSC
 * @date 2022/3/3
 */
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("regist")
    public ResultInfo regist(User user){
        return userService.regist(user);
    }
    @PostMapping("login")
    public ResultInfo login(String name,String pwd){
        return userService.login(name,pwd);
    }
}


