package com.pj.project.orders;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pj.project.goods.MyGoods;
import com.pj.project.goods.MyGoodsMapper;
import com.pj.project.user.MyUser;
import com.pj.project.user.MyUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mapper: orders -- Orders
 * @author xyy 
 */

@Service
public class MyOrdersService extends ServiceImpl<MyOrdersMapper, MyOrders> {

    @Autowired
    MyOrdersMapper myOrdersMapper;
    @Autowired
    MyUserMapper myUserMapper;
    @Autowired
    MyGoodsMapper myGoodsMapper;

    @Transactional
    public double makeOrder(MyOrders order){


        //1.更新订单表
        myOrdersMapper.insert(order);

        //2.获取物品、拥有者、购买者
        Integer ownerId = order.getOwnerId();
        Integer buyerId = order.getBuyerId();
        Integer goodId = order.getGoodId();
        MyUser owner = myUserMapper.selectById(ownerId);
        MyUser buyer = myUserMapper.selectById(buyerId);
        MyGoods good = myGoodsMapper.selectById(goodId);

        //3.判断钱够不够
        if(buyer.getMoney()<good.getPrice()){
            throw new RuntimeException("钱不够");
        }

        //4.物品拥有者加钱
        owner.setMoney(owner.getMoney()+good.getPrice());
        myUserMapper.updateById(owner);

        //5.购买者减钱
        double money=buyer.getMoney()-good.getPrice();
        buyer.setMoney(money);
        myUserMapper.updateById(buyer);

        //6.物品状态为已售出
        good.setStatus("1");
        myGoodsMapper.updateById(good);

        //7.返回购买者当前的余额
        return money;
    }

}
