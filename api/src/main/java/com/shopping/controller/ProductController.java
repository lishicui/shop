package com.shopping.controller;

import com.shopping.result.ResultInfo;
import com.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author LSC
 * @date 2022/3/7
 */
@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("getProductList")
    public ResultInfo getProductList(){
        return productService.getProductList();
    }
    @GetMapping("getProductInfo")
    public ResultInfo getProductInfo(int id){
        return productService.getProductInfo(id);
    }
}
