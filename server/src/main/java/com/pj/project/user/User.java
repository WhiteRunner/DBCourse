package com.pj.project.user;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: user -- User
 * @author xyy 
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "user";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "user";	


	// ---------- 表中字段 ----------
	/**
	 * id 
	 */
	public Integer id;	

	/**
	 * username 
	 */
	public String username;	

	/**
	 * password 
	 */
	public String password;	

	/**
	 * money 
	 */
	public Double money;	



	// ---------- 额外字段 ----------
	/**
	 * rate 
	 */
	public Double rate;	



	


}
