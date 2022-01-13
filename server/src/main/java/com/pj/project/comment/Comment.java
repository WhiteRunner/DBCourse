package com.pj.project.comment;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model: comment -- Comment
 * @author xyy 
 */
@Data
@Accessors(chain = true)
public class Comment implements Serializable {

	// ---------- 模块常量 ----------
	/**
	 * 序列化版本id 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 此模块对应的表名 
	 */
	public static final String TABLE_NAME = "comment";	
	/**
	 * 此模块对应的权限码 
	 */
	public static final String PERMISSION_CODE = "comment";	


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
	 * to_userId 
	 */
	public Integer toUid;	

	/**
	 * detail 
	 */
	public String detail;	

	/**
	 * rete (1=1, 2=2, 3=3, 4=4, 5=5) 
	 */
	public Integer rate;	



	// ---------- 额外字段 ----------
	/**
	 * username 
	 */
	public String username;	

	/**
	 * toUsername 
	 */
	public String toUsername;	



	


}
