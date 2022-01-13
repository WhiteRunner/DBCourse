package com.pj.project.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：orders -- Orders
 * @author xyy 
 *
 */
@Component
public class OrdersUtil {

	
	/** 底层 Mapper 对象 */
	public static OrdersMapper ordersMapper;
	@Autowired
	private void setOrdersMapper(OrdersMapper ordersMapper) {
		OrdersUtil.ordersMapper = ordersMapper;
	}
	
	
	/** 
	 * 将一个 Orders 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(Orders o) {
		AjaxError.throwByIsNull(o.id, "[id] 不能为空");		// 验证: id 
		AjaxError.throwByIsNull(o.ownerId, "[ownerId] 不能为空");		// 验证: ownerId 
		AjaxError.throwByIsNull(o.buyerId, "[buyerId] 不能为空");		// 验证: buyerId 
		AjaxError.throwByIsNull(o.goodId, "[goodId] 不能为空");		// 验证: goodId 
	}

	/** 
	 * 获取一个Orders (方便复制代码用) [G] 
	 */ 
	static Orders getOrders() {
		Orders o = new Orders();	// 声明对象 
		o.id = 0;		// id 
		o.ownerId = 0;		// ownerId 
		o.buyerId = 0;		// buyerId 
		o.goodId = 0;		// goodId 
		return o;
	}
	
	
	
	
	
}
