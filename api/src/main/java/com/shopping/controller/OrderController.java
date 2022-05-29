package com.shopping.controller;

import com.shopping.eneity.Order;
import com.shopping.result.ResultInfo;
import com.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author LSC
 * @date 2022/4/19
 */
@RestController
@CrossOrigin
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("insertOrderByUId")
    public ResultInfo insertOrder(@RequestBody Order order){
        return orderService.insertOrder(order);
    }
    @GetMapping("getOrderList")
    public ResultInfo getOrderList(int uID,Long id){
        return orderService.getOrderList(uID,id);
    }
}
