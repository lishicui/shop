package com.shopping.service.impl;

import com.shopping.eneity.Product;
import com.shopping.mapper.ProductMapper;
import com.shopping.result.ResultInfo;
import com.shopping.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author LSC
 * @date 2022/3/7
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public ResultInfo getProductList(){
        List<Product> list = productMapper.getProductList();
        if(list==null){
            return ResultInfo.genErrorResultInfo("获取失败",10001);
        }else{
            return ResultInfo.genSuccessResultInfo(list,"获取成功",10000);
        }
    }
    @Override
    public ResultInfo getProductInfo(int id){
        Product product = productMapper.getProductInfo(id);
        if(product==null){
            return ResultInfo.genErrorResultInfo("获取失败",10001);
        }else{
            return ResultInfo.genSuccessResultInfo(product,"获取成功",10000);
        }
    }

}
