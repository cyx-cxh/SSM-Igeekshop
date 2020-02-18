package com.igeekshop.ssm.service.impl;

import com.igeekshop.ssm.dao.OrderitemMapper;
import com.igeekshop.ssm.dao.OrdersMapper;
import com.igeekshop.ssm.domain.Orderitem;
import com.igeekshop.ssm.domain.Orders;
import com.igeekshop.ssm.domain.OrdersExample;
import com.igeekshop.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    OrderitemMapper orderitemMapper;
    @Override
    public void submitOrders(Orders orders) {
        ordersMapper.insert(orders);
        List<Orderitem> orderItems = orders.getOrderItems();
        for (Orderitem orderitem:orderItems)
        {
            System.out.println("orderitem"+orderitem);
            orderitemMapper.insert(orderitem);
        }


    }

    @Override
    public void updateOrderInfo(Orders orders) {
        ordersMapper.updateByPrimaryKey(orders);
    }

    @Override
    public List<Orders> selectOrdersByUid(String uid) {
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria = ordersExample.createCriteria();
        criteria.andUidEqualTo(uid);

        return  ordersMapper.selectByExample(ordersExample);
    }

    @Override
    public List<Map<String, Object>> selectAllOrderItem(String oid) {
        return ordersMapper.selectAllOrderItem(oid);
    }


}
