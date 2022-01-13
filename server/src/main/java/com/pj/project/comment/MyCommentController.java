package com.pj.project.comment;

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
 * Controller: comment -- Comment
 * @author xyy 
 */
@RestController
@RequestMapping("/Comment/")
public class MyCommentController {

	/** 底层 Mapper 对象 */
	@Autowired
	CommentMapper commentMapper;

	/** 增 */  
	@RequestMapping("insert")
	public AjaxJson add(@RequestBody Comment c){
		commentMapper.add(c);
		c = commentMapper.getById(SP.publicMapper.getPrimarykey());
		return AjaxJson.getSuccessData(c);
	}
}
