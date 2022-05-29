package com.shopping.mapper;

import com.shopping.eneity.Cart;
import com.shopping.eneity.Order;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * @author LSC
 * @date 2022/4/19
 */
@Mapper
public interface OrderMapper {
    int insertOrder(Order order);
    int insertSubmitOrder(Long id,List<Cart> idList);
    List<Order> getOrderList(int uID,Long oId);
    int deleteProduct(List<Cart> idList,int uID);
}
