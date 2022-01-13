package com.pj.project.goods;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Model: goods -- Goods
 * @author xyy 
 */
@Data
@Accessors(chain = true)
@TableName("goods")
public class MyGoods implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "goods";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "goods";	


	// ---------- 表中字段 ----------
	/**
	 * id 
	 */
	public Integer id;	

	/**
	 * userId 
	 */
	public Integer uid;	

	/**
	 * goodName 
	 */
	public String gname;	

	/**
	 * price 
	 */
	public Double price;	

	/**
	 * pic 
	 */
	public String pic;	

	/**
	 * status (0=unsold, 1=sold) 
	 */
	public String status;	

	/**
	 * remark 
	 */
	public String remark;	

	/**
	 * catagoryId 
	 */
	public Integer cid;	





	


}
