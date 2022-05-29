package com.shopping.eneity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author LSC
 * @date 2022/4/19
 */
@Data
public class Order {
    /*判断版本一致性*/
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonProperty("uID")
    private Integer uID;
    private String name;
    private String phone;
    private String address;
    private String district;
    private String type;
    private String code;

    private Double totalPrice;
    private Double actuallyPrice;
    private Double discounts;
    private Integer quantity;
    private Double postage;

    private String productIds;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    private String paymentWay;
    private String deliverWay;
    private String bill;

    private String state;
    private List<Product> pList;
}
