package com.shopping.eneity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author LSC
 * @date 2022/3/3
 */
@Data
public class Address {
    /*判断版本一致性*/
    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonProperty("uID")
    private Integer uID;
    private String name;
    private String phone;
    private String address;
    private String district;
    private String type;
    private String code;
    private String flag;
}