package com.shopping.mapper;

import com.shopping.eneity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LSC
 * @date 2022/3/3
 */
@Mapper
public interface UserMapper {
    User getUserByUserName(String name);
    int insert(User user);
    User getUser(String name,String pwd);
}
