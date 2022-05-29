package com.shopping.mapper;

import com.shopping.eneity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LSC
 * @date 2022/4/15
 */
@Mapper
public interface AddressMapper {
    List<Address> getAddressList(int uId);
    int updateAddress(Address address);
    int updateFlag(int id,int uId);
    int insertAddress(Address address);
    int deleteAddress(int id);
}
