package com.shopping.eneity;

import lombok.Data;

/**
 * @author LSC
 * @date 2022/3/3
 */
@Data
public class User {
    /*判断版本一致性*/
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String pwd;
    private String phone;
    private String address;
}
