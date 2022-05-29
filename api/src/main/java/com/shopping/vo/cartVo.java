package com.shopping.vo;
import com.shopping.eneity.Cart;
import lombok.Data;
/**
 * @author LSC
 * @date 2022/4/2
 */
@Data
public class cartVo extends Cart {
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
