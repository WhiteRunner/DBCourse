package com.pj.project.category;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: category -- Category
 * @author xyy 
 */
@Data
@Accessors(chain = true)
public class Category implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "category";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "category";	


	// ---------- 表中字段 ----------
	/**
	 * id 
	 */
	public Integer id;	

	/**
	 * name 
	 */
	public String name;	





	


}
