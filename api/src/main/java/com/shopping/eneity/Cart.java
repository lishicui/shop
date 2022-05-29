package com.shopping.eneity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author LSC
 * @date 2022/3/8
 */
@Data
public class Cart {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @JsonProperty("uID")
    private Integer uID;
    private Integer productID;
    private Integer quantity;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String color;
    private String size;
    private String capacity;
}
