package com.shopping.service;

import com.shopping.eneity.Address;
import com.shopping.result.ResultInfo;
import org.springframework.stereotype.Service;
/**
 * @author LSC
 * @date 2022/4/15
 */
@Service
public interface AddressService {
    ResultInfo getAddressList(int uId);
    ResultInfo updateAddress(Address address);
    ResultInfo insertAddress(Address address);
    ResultInfo deleteAddress(int id);
}
