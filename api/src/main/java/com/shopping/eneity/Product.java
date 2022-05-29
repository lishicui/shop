package com.shopping.eneity;

import lombok.Data;

/**
 * @author LSC
 * @date 2022/3/3
 */
@Data
public class Product {
    /*判断版本一致性*/
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String productName;
    private Double price;
    private String number;
    private String cover;
    private String type;
    private String content;
    private Double discount;
    private String shopName;
    private String colors;
    private String sizes;
    private String capacitys;
}
