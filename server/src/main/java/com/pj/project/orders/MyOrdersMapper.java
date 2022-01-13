package com.pj.project.orders;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pj.utils.so.SoMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper: orders -- Orders
 * @author xyy 
 */

@Mapper
@Repository
public interface MyOrdersMapper extends BaseMapper<MyOrders> {

     double priceSum();

     List<OrderCount> getOrderCount();
}
