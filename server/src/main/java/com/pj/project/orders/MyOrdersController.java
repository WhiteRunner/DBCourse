package com.pj.project.orders;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.pj.current.satoken.StpUserUtil;
import com.pj.project4sp.SP;
import com.pj.utils.sg.AjaxError;
import com.pj.utils.sg.AjaxJson;
import com.pj.utils.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Controller: orders -- Orders
 * @author xyy 
 */
@RestController
@RequestMapping("/Orders/")
public class MyOrdersController {

	/** 底层 Mapper 对象 */
	@Autowired
	MyOrdersMapper myOrdersMapper;
	@Autowired
	MyOrdersService myOrdersService;


	@RequestMapping("makeOrder")
	public AjaxJson getList(@RequestBody MyOrders order) {
		double newMoney = myOrdersService.makeOrder(order);
		return AjaxJson.getSuccess("购买成功",newMoney);
	}

	@RequestMapping("cnt")
	public AjaxJson cnt() {
		int count = myOrdersService.count();
		return AjaxJson.getSuccess("成功返回",count);
	}

	@RequestMapping("priceSum")
	public AjaxJson priceSum() {
		double sum = myOrdersMapper.priceSum();
		return AjaxJson.getSuccess("成功返回",sum);
	}

	@RequestMapping("getOrderCount")
	public AjaxJson getClsCount() {

		List<OrderCount> orderCount = myOrdersMapper.getOrderCount();
		return AjaxJson.getSuccess("成功返回",orderCount);
	}
	
	
	
	

}
