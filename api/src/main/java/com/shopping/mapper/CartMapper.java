package com.shopping.mapper;
import com.shopping.eneity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LSC
 * @date 2022/3/8
 */
@Mapper
public interface CartMapper {
    Cart getProductToCart(int uID,int productID);
    int updateProductToCart(Cart cart);
    int addProductToCart(Cart cart);
    List<Cart> getCartList(int uID);
    int deleteProduct(List<String> idList,int uID);
}
