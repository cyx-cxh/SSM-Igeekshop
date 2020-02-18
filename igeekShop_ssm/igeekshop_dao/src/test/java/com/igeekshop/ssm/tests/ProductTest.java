package com.igeekshop.ssm.tests;

import com.igeekshop.ssm.dao.OrdersMapper;
import com.igeekshop.ssm.dao.ProductMapper;
import com.igeekshop.ssm.domain.Orders;
import com.igeekshop.ssm.domain.OrdersExample;
import com.igeekshop.ssm.domain.Product;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class ProductTest {
    //加载配置文件
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "spring-mybatis.xml"});
    ProductMapper productMapper = applicationContext.getBean(ProductMapper.class);
    OrdersMapper ordersMapper = applicationContext.getBean(OrdersMapper.class);
    @Test
    public void test01()
    {

        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria = ordersExample.createCriteria();
        criteria.andUidEqualTo("1");

        List<Orders> orders = ordersMapper.selectByExample(ordersExample);
        System.out.println(orders);

    }
}
