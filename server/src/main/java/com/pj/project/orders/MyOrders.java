package com.pj.project.orders;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Model: orders -- Orders
 * @author xyy 
 */
@Data
@Accessors(chain = true)
@TableName("orders")
public class MyOrders implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "orders";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "orders";	


	// ---------- 表中字段 ----------
	/**
	 * id 
	 */
	public Integer id;	

	/**
	 * ownerId 
	 */
	public Integer ownerId;	

	/**
	 * buyerId 
	 */
		public Integer buyerId;

	/**
	 * goodId 
	 */
	public Integer goodId;


}
