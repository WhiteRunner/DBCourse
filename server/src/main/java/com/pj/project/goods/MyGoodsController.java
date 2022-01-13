package com.pj.project.goods;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Controller: goods -- Goods
 * @author xyy 
 */
@RestController
@RequestMapping("/Goods/")
public class MyGoodsController {

	/** 底层 Mapper 对象 */
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	MyGoodsMapper myGoodsMapper;


	@RequestMapping("addGood")
	public AjaxJson addGood(@RequestBody Goods goods){
		System.out.println(goods);
		goodsMapper.add(goods);
		return AjaxJson.getSuccess("上传成功");
	}

	@RequestMapping("getPurchasedGood")
	public AjaxJson getPurchasedGood(Integer[] ids){

		//读取不包括用户名的商品
		//List<Integer> integers = Arrays.asList(ids);
		//List<MyGoods> myGoods = myGoodsMapper.selectBatchIds(integers);

		ArrayList<Goods> goods = new ArrayList<>();
		for (Integer id : ids) {
			Goods good = goodsMapper.getById(id);
			goods.add(good);
		}
		return AjaxJson.getSuccess("返回成功",goods);
	}

	@RequestMapping("cnt")
	public AjaxJson cnt(){
		Integer integer = myGoodsMapper.selectCount(null);
		return AjaxJson.getSuccess("成功返回",integer);
	}

	@RequestMapping("getTypeCount")
	public AjaxJson getTypeCount(){
		List<TypeCount> typeCount = myGoodsMapper.getTypeCount();
		return AjaxJson.getSuccess("成功返回",typeCount);
	}
	
	
	

}
