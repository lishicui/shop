package com.shopping.mapper;

import com.shopping.eneity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LSC
 * @date 2022/3/7
 */
@Mapper
public interface ProductMapper {
    List<Product> getProductList();
    Product getProductInfo(int id);
}
