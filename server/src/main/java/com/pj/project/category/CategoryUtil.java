package com.pj.project.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：category -- Category
 * @author xyy 
 *
 */
@Component
public class CategoryUtil {

	
	/** 底层 Mapper 对象 */
	public static CategoryMapper categoryMapper;
	@Autowired
	private void setCategoryMapper(CategoryMapper categoryMapper) {
		CategoryUtil.categoryMapper = categoryMapper;
	}
	
	
	/** 
	 * 将一个 Category 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(Category c) {
		AjaxError.throwByIsNull(c.id, "[id] 不能为空");		// 验证: id 
		AjaxError.throwByIsNull(c.name, "[name] 不能为空");		// 验证: name 
	}

	/** 
	 * 获取一个Category (方便复制代码用) [G] 
	 */ 
	static Category getCategory() {
		Category c = new Category();	// 声明对象 
		c.id = 0;		// id 
		c.name = "";		// name 
		return c;
	}
	
	
	
	
	
}
