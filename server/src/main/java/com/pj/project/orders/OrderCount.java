package com.pj.project.orders;

import lombok.Data;

/**
 * @author WhiteRunner
 * @create 2022-01-01 23:12
 */
@Data
public class OrderCount {
    private String name; //类别
    private Integer count; //该类别的订单数
    private double priceSum; //该类别订单金额总计
}
