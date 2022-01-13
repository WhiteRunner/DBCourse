package com.pj.project.goods;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pj.utils.so.SoMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper: goods -- Goods
 * @author xyy 
 */

@Mapper
@Repository
public interface MyGoodsMapper extends BaseMapper<MyGoods> {
    List<TypeCount> getTypeCount();

}
