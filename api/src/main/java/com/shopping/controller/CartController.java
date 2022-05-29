package com.shopping.controller;

import com.shopping.eneity.Cart;
import com.shopping.result.ResultInfo;
import com.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author LSC
 * @date 2022/3/8
 */
@RestController
@CrossOrigin
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("addProductToCart")
    public ResultInfo addProductToCart(Cart cart){
        return cartService.addProductToCart(cart);
    }
    @GetMapping("getCartListByUId")
    public ResultInfo getCartList(int uID){
        return cartService.getCartList(uID);
    }
    @PostMapping("deleteProductById")
    public ResultInfo deleteProduct(String ids,int uID){
        return cartService.deleteProduct(ids,uID);
    }
}
