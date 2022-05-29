package com.shopping.service;

import com.shopping.result.ResultInfo;
import org.springframework.stereotype.Service;
/**
 * @author LSC
 * @date 2022/3/7
 */
public interface ProductService {
    ResultInfo getProductList();
    ResultInfo getProductInfo(int id);
}
