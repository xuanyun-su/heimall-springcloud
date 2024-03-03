package com.hmall.users.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.users.domain.po.Address;
import com.hmall.users.mapper.AddressMapper;
import com.hmall.users.service.IAddressService;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
