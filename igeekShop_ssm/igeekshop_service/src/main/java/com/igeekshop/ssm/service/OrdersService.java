package com.igeekshop.ssm.service;

import com.igeekshop.ssm.domain.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersService {

   public void submitOrders(Orders orders);
   public void  updateOrderInfo(Orders orders);
   public List<Orders> selectOrdersByUid( String uid);
   List<Map<String,Object>>selectAllOrderItem(String oid);
}
