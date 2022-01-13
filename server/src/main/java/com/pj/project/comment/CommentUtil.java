package com.pj.project.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：comment -- Comment
 * @author xyy 
 *
 */
@Component
public class CommentUtil {

	
	/** 底层 Mapper 对象 */
	public static CommentMapper commentMapper;
	@Autowired
	private void setCommentMapper(CommentMapper commentMapper) {
		CommentUtil.commentMapper = commentMapper;
	}
	
	
	/** 
	 * 将一个 Comment 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(Comment c) {
		AjaxError.throwByIsNull(c.id, "[id] 不能为空");		// 验证: id 
		AjaxError.throwByIsNull(c.uid, "[userId] 不能为空");		// 验证: userId 
		AjaxError.throwByIsNull(c.toUid, "[to_userId] 不能为空");		// 验证: to_userId 
		AjaxError.throwByIsNull(c.detail, "[detail] 不能为空");		// 验证: detail 
		AjaxError.throwByIsNull(c.rate, "[rete] 不能为空");		// 验证: rete (1=1, 2=2, 3=3, 4=4, 5=5) 
	}

	/** 
	 * 获取一个Comment (方便复制代码用) [G] 
	 */ 
	static Comment getComment() {
		Comment c = new Comment();	// 声明对象 
		c.id = 0;		// id 
		c.uid = 0;		// userId 
		c.toUid = 0;		// to_userId 
		c.detail = "";		// detail 
		c.rate = 0;		// rete (1=1, 2=2, 3=3, 4=4, 5=5) 
		return c;
	}
	
	
	
	
	
}
