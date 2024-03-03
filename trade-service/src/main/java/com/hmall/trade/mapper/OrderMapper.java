package com.hmall.trade.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmall.trade.domain.po.Order;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
