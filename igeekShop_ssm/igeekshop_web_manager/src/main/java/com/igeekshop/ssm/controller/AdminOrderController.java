package com.igeekshop.ssm.controller;

import com.igeekshop.ssm.dao.OrdersMapper;
import com.igeekshop.ssm.domain.Orderitem;
import com.igeekshop.ssm.domain.Orders;
import com.igeekshop.ssm.domain.Product;
import com.igeekshop.ssm.domain.Users;


import com.igeekshop.ssm.service.OrdersService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Controller
public class AdminOrderController {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrdersService ordersService;
    @RequestMapping("orderList")
    public  String orderList(ModelMap map)
    {

        List<Orders> orderList = ordersMapper.selectByExample(null);
        map.put("orderList",orderList);
        return "/order/list";

    }
    @RequestMapping("orderinfo")
    public  String orderinfo( String oid,ModelMap map1)
    {

        System.out.println(oid);

                List<Map<String, Object>> listMap = ordersService.selectAllOrderItem(oid);
        System.out.println(listMap);
        map1.put("listMap",listMap);
//        map1.put("oid",oid);
        map1.addAttribute("oid",oid);
       return "/order/order_info";


    }
}
