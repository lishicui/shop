package com.shopping.service.impl;


import com.alibaba.fastjson.JSON;
import com.shopping.eneity.Cart;
import com.shopping.eneity.Order;
import com.shopping.mapper.OrderMapper;
import com.shopping.service.OrderService;
import com.shopping.result.ResultInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author LSC
 * @date 2022/4/19
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public ResultInfo insertOrder(Order order){
        long id = order.getUID() + System.currentTimeMillis();
        order.setId(id);
        int insert = orderMapper.insertOrder(order);
        if (insert > 0){
            String ids = order.getProductIds();
            List<Cart> idList = JSON.parseArray(ids,Cart.class);
            System.out.println(idList);
            orderMapper.insertSubmitOrder(id,idList);
            int uID = order.getUID();
            orderMapper.deleteProduct(idList,uID);
            return ResultInfo.genSuccessResultInfo(null,"提交成功",10000);
        }else{
            return ResultInfo.genErrorResultInfo("提交失败",10001);
        }
    }
    @Override
    public ResultInfo getOrderList(int uID,Long id){
        List<Order> list = orderMapper.getOrderList(uID,id);
        if(list==null){
            return ResultInfo.genErrorResultInfo("获取失败",10001);
        }else{
            return ResultInfo.genSuccessResultInfo(list,"获取成功",10000);
        }
    }
}
