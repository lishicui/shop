package com.shopping.service;

import com.shopping.eneity.Order;
import com.shopping.result.ResultInfo;
import org.springframework.stereotype.Service;
/**
 * @author LSC
 * @date 2022/4/19
 */
@Service
public interface OrderService {
    ResultInfo insertOrder(Order order);
    ResultInfo getOrderList(int uID,Long id);
}
