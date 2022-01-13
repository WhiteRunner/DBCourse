package com.pj.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：user -- User
 * @author xyy 
 *
 */
@Component
public class UserUtil {

	
	/** 底层 Mapper 对象 */
	public static UserMapper userMapper;
	@Autowired
	private void setUserMapper(UserMapper userMapper) {
		UserUtil.userMapper = userMapper;
	}
	
	
	/** 
	 * 将一个 User 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(User u) {
		AjaxError.throwByIsNull(u.id, "[id] 不能为空");		// 验证: id 
		AjaxError.throwByIsNull(u.username, "[username] 不能为空");		// 验证: username 
		AjaxError.throwByIsNull(u.password, "[password] 不能为空");		// 验证: password 
		AjaxError.throwByIsNull(u.money, "[money] 不能为空");		// 验证: money 
	}

	/** 
	 * 获取一个User (方便复制代码用) [G] 
	 */ 
	static User getUser() {
		User u = new User();	// 声明对象 
		u.id = 0;		// id 
		u.username = "";		// username 
		u.password = "";		// password 
		u.money = 0.0;		// money 
		return u;
	}
	
	
	
	
	
}
