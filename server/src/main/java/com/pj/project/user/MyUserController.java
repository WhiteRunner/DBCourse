package com.pj.project.user;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pj.current.satoken.StpUserUtil;
import com.pj.project4sp.SP;
import com.pj.utils.sg.AjaxError;
import com.pj.utils.sg.AjaxJson;
import com.pj.utils.so.SoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


/**
 * Controller: user -- 
 * @author xyy 
 */
@RestController
@RequestMapping("/User/")
public class MyUserController {

	/** 底层 Mapper 对象 */
	@Autowired
	MyUserMapper userMapper;


	@RequestMapping("login")
	public AjaxJson Login(@RequestBody MyUser user) {
		//md5加密密码
		String password = user.getPassword();
		user.setPassword(SaSecureUtil.md5(password));
		//查询数据库
		LambdaQueryWrapper<MyUser> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(MyUser::getUsername,user.username);
		wrapper.eq(MyUser::getPassword,user.password);
		MyUser u = userMapper.selectOne(wrapper);
		if(u==null){
			return AjaxJson.getError("没有该用户或密码错误");
		}else{
			StpUtil.login(u.getId());
			SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
			HashMap<String, Object> map = new HashMap<>();
			map.put("tokenInfo",tokenInfo);
			map.put("id",u.getId());
			return AjaxJson.getSuccess("登录成功",map);
		}
	}

	@RequestMapping("register")
	public AjaxJson Register(@RequestBody JSONObject obj) {
		String username = (String) obj.get("username");
		String password = (String) obj.get("password");
		password = SaSecureUtil.md5(password);
		//数据库有没有该用户
		LambdaQueryWrapper<MyUser> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(MyUser::getUsername,username);
		MyUser u = userMapper.selectOne(wrapper);
		//添加用户
		if (u != null) {
		    return AjaxJson.getError("有重名用户");
		}else{
			MyUser user = new MyUser();
			user.setUsername(username);
			user.setPassword(password);
			//System.out.println("-------------------------------------"+user);
			user.setMoney(10000.0);
			userMapper.insert(user);
			return AjaxJson.getSuccess("成功添加用户");
		}

	}


	@RequestMapping("checkToken")
	public AjaxJson checkToken() {
		if(StpUtil.isLogin()){
			return AjaxJson.getSuccess("已登录");
		}else{
			return AjaxJson.getSuccess("未登录");
		}
	}

	@RequestMapping("findUserInfo/{id}")
	public AjaxJson findUserInfo( @PathVariable("id")Integer id) {
		MyUser user = userMapper.selectById(id);
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",user.getId());
		map.put("username",user.getUsername());
		map.put("money",user.getMoney());
		return AjaxJson.getSuccess("返回个人信息成功",map);
	}

	@RequestMapping("cnt")
	public AjaxJson cnt() {
		Integer integer = userMapper.selectCount(null);
		return AjaxJson.getSuccess("成功返回",integer);
	}

}
