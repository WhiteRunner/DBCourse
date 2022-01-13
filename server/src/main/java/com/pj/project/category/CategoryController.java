package com.pj.project.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.pj.utils.sg.*;
import com.pj.utils.so.*;
import com.pj.project4sp.SP;

import com.pj.current.satoken.StpUserUtil;
import cn.dev33.satoken.annotation.SaCheckPermission;


/**
 * Controller: category -- Category
 * @author xyy 
 */
@RestController
@RequestMapping("/Category/")
public class CategoryController {

	/** 底层 Mapper 对象 */
	@Autowired
	CategoryMapper categoryMapper;

	/** 增 */  
	@RequestMapping("add")
	@SaCheckPermission(Category.PERMISSION_CODE)
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(Category c){
		categoryMapper.add(c);
		c = categoryMapper.getById(SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(c);
	}

	/** 删 */  
	@RequestMapping("delete")
	@SaCheckPermission(Category.PERMISSION_CODE)
	public AjaxJson delete(Integer id){
		int line = categoryMapper.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
	@RequestMapping("deleteByIds")
	@SaCheckPermission(Category.PERMISSION_CODE)
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class); 
		int line = SP.publicMapper.deleteByIds(Category.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}
	
	/** 改 */  
	@RequestMapping("update")
	@SaCheckPermission(Category.PERMISSION_CODE)
	public AjaxJson update(Category c){
		int line = categoryMapper.update(c);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Integer id){
		Category c = categoryMapper.getById(id);
		return AjaxJson.getSuccessData(c);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() { 
		SoMap so = SoMap.getRequestSoMap();
		List<Category> list = categoryMapper.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}
	
	
	
	// ------------------------- 前端接口 -------------------------
	
	
	/** 改 - 不传不改 [G] */
	@RequestMapping("updateByNotNull")
	public AjaxJson updateByNotNull(Integer id){
		AjaxError.throwBy(true, "如需正常调用此接口，请删除此行代码");
		// 鉴别身份，是否为数据创建者 
		long userId = SP.publicMapper.getColumnByIdToLong(Category.TABLE_NAME, "user_id", id);
		AjaxError.throwBy(userId != StpUserUtil.getLoginIdAsLong(), "此数据您无权限修改");
		// 开始修改 (请只保留需要修改的字段)
		SoMap so = SoMap.getRequestSoMap();
		so.clearNotIn("id", "name").clearNull().humpToLineCase();	
		int line = SP.publicMapper.updateBySoMapById(Category.TABLE_NAME, so, id);
		return AjaxJson.getByLine(line);
	}
	
	
	
	
	
	

}
