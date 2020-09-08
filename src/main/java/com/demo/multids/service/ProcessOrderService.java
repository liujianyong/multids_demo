package com.demo.multids.service;

import com.demo.multids.dao.OrderExpMapper;
import com.demo.multids.model.OrderExp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ProcessOrderService {
    public final static short UNPAY = 0; //未支付
    public final static short PAYPED = 1; // 已支付
    public final static short EXPIRED = -1; // 已过期

    @Autowired
    private OrderExpMapper orderExpMapper;

    public void insertOrders(int orderNumber) {
        Random r = new Random();
        OrderExp orderExp;
        for (int i = 0; i < orderNumber; i++) {
            long expireTime = r.nextInt(20) + 5; // 订单的超时时长，单位秒 5~25
            orderExp = new OrderExp();
            String orderNo = "DD00_" + expireTime + "S"; // 订单的编号
            orderExp.setOrderNo(orderNo);
            orderExp.setOrderNote("海王五排" + orderNo + "号，过期时长：" + expireTime);
            orderExp.setExpireTime(new Date(expireTime));
            orderExp.setInsertTime(new Date());
            orderExp.setOrderStatus(0);
            orderExpMapper.insert(orderExp);
        }
    }


    public void findOrders() {
        List<OrderExp> orderList = orderExpMapper.selectUnPayOrders();
        System.out.println("发现表中还有[" + orderList.size() + "]个到期未支付的订单");
        for (OrderExp order: orderList) {
            System.out.println(order.toString());
        }
    }


}
