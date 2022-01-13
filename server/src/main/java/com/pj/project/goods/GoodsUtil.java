package com.pj.project.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：goods -- Goods
 * @author xyy 
 *
 */
@Component
public class GoodsUtil {

	
	/** 底层 Mapper 对象 */
	public static GoodsMapper goodsMapper;
	@Autowired
	private void setGoodsMapper(GoodsMapper goodsMapper) {
		GoodsUtil.goodsMapper = goodsMapper;
	}
	
	
	/** 
	 * 将一个 Goods 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G] 
	 */
	static void check(Goods g) {
		AjaxError.throwByIsNull(g.id, "[id] 不能为空");		// 验证: id 
		AjaxError.throwByIsNull(g.uid, "[userId] 不能为空");		// 验证: userId 
		AjaxError.throwByIsNull(g.gname, "[goodName] 不能为空");		// 验证: goodName 
		AjaxError.throwByIsNull(g.price, "[price] 不能为空");		// 验证: price 
		AjaxError.throwByIsNull(g.pic, "[pic] 不能为空");		// 验证: pic 
		AjaxError.throwByIsNull(g.status, "[status] 不能为空");		// 验证: status (0=unsold, 1=sold) 
		AjaxError.throwByIsNull(g.remark, "[remark] 不能为空");		// 验证: remark 
		AjaxError.throwByIsNull(g.cid, "[catagoryId] 不能为空");		// 验证: catagoryId 
	}

	/** 
	 * 获取一个Goods (方便复制代码用) [G] 
	 */ 
	static Goods getGoods() {
		Goods g = new Goods();	// 声明对象 
		g.id = 0;		// id 
		g.uid = 0;		// userId 
		g.gname = "";		// goodName 
		g.price = 0.0;		// price 
		g.pic = "";		// pic 
		g.status = "";		// status (0=unsold, 1=sold) 
		g.remark = "";		// remark 
		g.cid = 0;		// catagoryId 
		return g;
	}
	
	
	
	
	
}
