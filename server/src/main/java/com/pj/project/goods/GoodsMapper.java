package com.pj.project.goods;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pj.utils.so.*;
import org.springframework.stereotype.Repository;

/**
 * Mapper: goods -- Goods
 * @author xyy 
 */

@Mapper
@Repository
public interface GoodsMapper {

	/**
	 * 增  
	 * @param g 实体对象 
	 * @return 受影响行数 
	 */
	int add(Goods g);

	/**
	 * 删  
	 * @param id 要删除的数据id  
	 * @return 受影响行数 
	 */
	int delete(Integer id);	 

	/** 
	 * 改  
	 * @param g 实体对象 
	 * @return 受影响行数 
	 */
	int update(Goods g);

	/** 
	 * 查 - 根据id  
	 * @param id 要查询的数据id 
	 * @return 实体对象 
	 */
	Goods getById(Integer id);	 

	/**
	 * 查集合 - 根据条件（参数为空时代表忽略指定条件）
	 * @param so 参数集合 
	 * @return 数据列表 
	 */
	List<Goods> getList(SoMap so);


}
