package com.pj.project.comment;

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
 * Controller: comment -- Comment
 * @author xyy 
 */
@RestController
@RequestMapping("/Comment/")
public class CommentController {

	/** 底层 Mapper 对象 */
	@Autowired
	CommentMapper commentMapper;

	/** 增 */  
	@RequestMapping("add")
	@SaCheckPermission(Comment.PERMISSION_CODE)
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson add(Comment c){
		commentMapper.add(c);
		c = commentMapper.getById(SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(c);
	}

	/** 删 */  
	@RequestMapping("delete")
	@SaCheckPermission(Comment.PERMISSION_CODE)
	public AjaxJson delete(Integer id){
		int line = commentMapper.delete(id);
		return AjaxJson.getByLine(line);
	}
	
	/** 删 - 根据id列表 */  
	@RequestMapping("deleteByIds")
	@SaCheckPermission(Comment.PERMISSION_CODE)
	public AjaxJson deleteByIds(){
		List<Long> ids = SoMap.getRequestSoMap().getListByComma("ids", long.class); 
		int line = SP.publicMapper.deleteByIds(Comment.TABLE_NAME, ids);
		return AjaxJson.getByLine(line);
	}
	
	/** 改 */  
	@RequestMapping("update")
	@SaCheckPermission(Comment.PERMISSION_CODE)
	public AjaxJson update(Comment c){
		int line = commentMapper.update(c);
		return AjaxJson.getByLine(line);
	}

	/** 查 - 根据id */  
	@RequestMapping("getById")
	public AjaxJson getById(Integer id){
		Comment c = commentMapper.getById(id);
		return AjaxJson.getSuccessData(c);
	}

	/** 查集合 - 根据条件（参数为空时代表忽略指定条件） */  
	@RequestMapping("getList")
	public AjaxJson getList() { 
		SoMap so = SoMap.getRequestSoMap();
		List<Comment> list = commentMapper.getList(so.startPage());
		return AjaxJson.getPageData(so.getDataCount(), list);
	}
	
	
	
	// ------------------------- 前端接口 -------------------------
	
	
	/** 改 - 不传不改 [G] */
	@RequestMapping("updateByNotNull")
	public AjaxJson updateByNotNull(Integer id){
		AjaxError.throwBy(true, "如需正常调用此接口，请删除此行代码");
		// 鉴别身份，是否为数据创建者 
		long userId = SP.publicMapper.getColumnByIdToLong(Comment.TABLE_NAME, "user_id", id);
		AjaxError.throwBy(userId != StpUserUtil.getLoginIdAsLong(), "此数据您无权限修改");
		// 开始修改 (请只保留需要修改的字段)
		SoMap so = SoMap.getRequestSoMap();
		so.clearNotIn("id", "uid", "toUid", "detail", "rate").clearNull().humpToLineCase();	
		int line = SP.publicMapper.updateBySoMapById(Comment.TABLE_NAME, so, id);
		return AjaxJson.getByLine(line);
	}
	
	
	
	
	
	

}
