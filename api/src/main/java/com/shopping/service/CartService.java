package com.shopping.service;

import com.shopping.eneity.Cart;
import com.shopping.result.ResultInfo;
/**
 * @author LSC
 * @date 2022/3/8
 */
public interface CartService {
    ResultInfo addProductToCart(Cart cart);
    ResultInfo getCartList(int uID);
    ResultInfo deleteProduct(String ids,int uID);
}
