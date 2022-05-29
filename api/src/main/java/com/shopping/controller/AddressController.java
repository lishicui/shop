package com.shopping.controller;

import com.shopping.eneity.Address;
import com.shopping.result.ResultInfo;
import com.shopping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LSC
 * @date 2022/4/15
 */
@RestController
@CrossOrigin
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping("getAddressList")
    public ResultInfo getAddressList(int uId){
        return addressService.getAddressList(uId);
    }
    @PostMapping("updateAddressById")
    public ResultInfo updateAddress(Address address){
        return addressService.updateAddress(address);
    }
    @PostMapping("insertAddressByUId")
    public ResultInfo insertAddress(Address address){
        return addressService.insertAddress(address);
    }
    @PostMapping("deleteAddressById")
    public ResultInfo deleteAddress(int id){
        return addressService.deleteAddress(id);
    }
}