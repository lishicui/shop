package com.shopping.service;

import com.shopping.eneity.User;
import com.shopping.result.ResultInfo;
import org.springframework.stereotype.Service;

/**
 * @author LSC
 * @date 2022/3/3
 */
@Service
public interface UserService {

    ResultInfo regist(User user);

    ResultInfo login(String name,String pwd);


}
