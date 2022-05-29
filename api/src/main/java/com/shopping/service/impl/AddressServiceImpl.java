package com.shopping.service.impl;

import com.shopping.eneity.Address;
import com.shopping.mapper.AddressMapper;
import com.shopping.result.ResultInfo;
import com.shopping.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author LSC
 * @date 2022/4/15
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public ResultInfo getAddressList(int uId){
        List<Address> list = addressMapper.getAddressList(uId);
        if(list==null){
            return ResultInfo.genErrorResultInfo("获取失败",10001);
        }else{
            return ResultInfo.genSuccessResultInfo(list,"获取成功",10000);
        }
    }
    @Override
    public ResultInfo updateAddress(Address address){
        int update = addressMapper.updateAddress(address);
        if (update > 0){
            Boolean bol = address.getFlag().equals("1");
            if(bol){
                addressMapper.updateFlag(address.getId(),address.getUID());
            }
            return ResultInfo.genSuccessResultInfo(null,"修改成功",10000);
        }else{
            return ResultInfo.genErrorResultInfo("修改失败",10001);
        }
    }
    @Override
    public ResultInfo insertAddress(Address address){
        int insert = addressMapper.insertAddress(address);
        if (insert > 0){
            return ResultInfo.genSuccessResultInfo(null,"添加成功",10000);
        }else{
            return ResultInfo.genErrorResultInfo("添加失败",10001);
        }
    }
    @Override
    public ResultInfo deleteAddress(int id){
        int delete = addressMapper.deleteAddress(id);
        if (delete > 0){
            return ResultInfo.genSuccessResultInfo(null,"删除成功",10000);
        }else{
            return ResultInfo.genErrorResultInfo("删除失败",10001);
        }
    }


}
