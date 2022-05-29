package com.shopping.service.impl;

import com.shopping.eneity.Cart;
import com.shopping.mapper.CartMapper;
import com.shopping.result.ResultInfo;
import com.shopping.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Arrays;
import java.util.List;

/**
 * @author LSC
 * @date 2022/3/8
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public ResultInfo addProductToCart(Cart cart){
//        Cart cart = cartMapper.getProductToCart(uID,productID);
        int productID = cart.getProductID();
        int uID = cart.getUID();
        Cart cart1 = cartMapper.getProductToCart(uID,productID);
        if(cart1 != null){
            int update = cartMapper.updateProductToCart(cart);
            if (update>0){
                return ResultInfo.genSuccessResultInfo(null,"更改成功",10000);
            }else{
                return ResultInfo.genErrorResultInfo("更改失败",10001);
            }
        }else{
            int insert = cartMapper.addProductToCart(cart);
            if (insert>0){
                return ResultInfo.genSuccessResultInfo(null,"添加成功",10000);
            }else{
                return ResultInfo.genErrorResultInfo("添加失败",10001);
            }
        }
    };
    @Override
    public ResultInfo getCartList(int uID){
        List<Cart> list = cartMapper.getCartList(uID);
        if(list==null){
            return ResultInfo.genErrorResultInfo("获取失败",10001);
        }else{
            return ResultInfo.genSuccessResultInfo(list,"获取成功",10000);
        }
    }
    @Override
    public  ResultInfo deleteProduct(String ids,int uID){
        List<String> idList = Arrays.asList(ids.split(","));
        int delete = cartMapper.deleteProduct(idList,uID);
        if (delete>0){
            return ResultInfo.genSuccessResultInfo(null,"删除成功",10000);
        }else{
            return ResultInfo.genErrorResultInfo("删除失败",10001);
        }
    }
}
