package com.pj.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.project.category.CategoryMapper;
import com.pj.project.comment.CommentMapper;
import com.pj.project.goods.GoodsMapper;
import com.pj.project.orders.OrdersMapper;
import com.pj.project.user.UserMapper;
import com.pj.project4sp.public4mapper.PublicMapper;
import com.pj.project4sp.public4mapper.PublicService;

/**
 * SpringBean依赖清单，项目中所有Bean在此定义
 */
@Component
public class FC {

	// ======================================== 所有Mapper ============================================== 

	public static CategoryMapper categoryMapper;		// Mapper依赖：Category
	public static CommentMapper commentMapper;		// Mapper依赖：Comment
	public static GoodsMapper goodsMapper;		// Mapper依赖：Goods
	public static OrdersMapper ordersMapper;		// Mapper依赖：Orders
	public static UserMapper userMapper;		// Mapper依赖：User
	public static PublicMapper publicMapper;					// Mapper: 公共Mapper 



	// ======================================== 所有Service ============================================== 

	public static PublicService publicService;						// Service：公共service



	// ======================================== 所有注入所有Bean ============================================== 
	
	// 注入 
	@Autowired
	public void setBean(
			CategoryMapper categoryMapper, 
			CommentMapper commentMapper, 
			GoodsMapper goodsMapper, 
			OrdersMapper ordersMapper, 
			UserMapper userMapper, 
			PublicMapper publicMapper,
			PublicService publicService
			) {
			FC.categoryMapper = categoryMapper;
			FC.commentMapper = commentMapper;
			FC.goodsMapper = goodsMapper;
			FC.ordersMapper = ordersMapper;
			FC.userMapper = userMapper;
			FC.publicMapper = publicMapper;
			FC.publicService = publicService;
	}


}