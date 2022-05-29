package com.shopping.eneity;

import lombok.Data;
/**
 * @author LSC
 * @date 2022/4/25
 */
@Data
public class SubmitOrder {
    /*判断版本一致性*/
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Long orderID;
    private String productName;
    private Double price;
    private String number;
    private String cover;
    private String type;
    private String content;
    private Double discount;
    private String shopName;
    private Integer productID;
    private Integer num;
    private String color;
    private String size;
    private String capacity;
}
