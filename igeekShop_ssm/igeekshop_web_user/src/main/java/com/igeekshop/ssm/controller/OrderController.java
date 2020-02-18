package com.igeekshop.ssm.controller;

import com.igeekshop.ssm.domain.*;
import com.igeekshop.ssm.service.OrdersService;
import com.igeekshop.ssm.service.ProductService;


import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class OrderController {
@Autowired
private ProductService productService;
@Autowired
private OrdersService ordersService;
    @RequestMapping(value = "addCart",method = RequestMethod.GET)
    public  String  addCart(String pid, String buyNum, ModelMap map, HttpServletRequest request)
    {

        HttpSession session = request.getSession();
        Product product=productService.findById(pid);
        Integer buyNum1 = Integer.valueOf(buyNum);
        if (buyNum1!=null) {

            if (buyNum1<=0) {
                map.put("error", "你输入的数值"+buyNum1+"非法，请核对后再提交");
                return "error";
            }
        }
        double subTotal=buyNum1*product.getShopPrice();
        double newsubTotal=subTotal;
        Cart cart =(Cart)session.getAttribute("cart");
        if (cart==null) {
            cart=new Cart();
        }
        Map<String, CartItem> cartItems=cart.getCartItems();
        if (cartItems.containsKey(pid)) {
            int oldBuyNum=cartItems.get(pid).getBuyNum();
            buyNum1+=oldBuyNum;
            newsubTotal=buyNum1*product.getShopPrice();
        }
        CartItem cartItem=new CartItem(product,buyNum1,newsubTotal,pid);

        cart.getCartItems().put(pid,cartItem);
        cart.setTotal(cart.getTotal()+subTotal);

//        System.out.println(cart);
        session.setAttribute("cart", cart);

        return "cart";
    }
    @RequestMapping("/delFromCart")
    public String delFromCart(HttpSession session,String pid)
    {

        Cart cart = (Cart)session.getAttribute("cart");
        if (cart!=null) {
            Map<String, CartItem>list=cart.getCartItems();
            cart.setTotal(cart.getTotal()-list.get(pid).getSubTotal());
            list.remove(pid);
        }
        session.setAttribute("cart", cart);
        return "cart";
    }

    @RequestMapping("/clearCart")
    public String clearCart(HttpSession session)
    {
        session.removeAttribute("cart");
        return "cart";
    }



    @RequestMapping("/submitOrder")
    public String submitOrder(HttpSession session)
    {

        Users user=(Users) session.getAttribute("loginUser");
        System.out.println(user);
        if (user==null) {

            return  "login";
        }
        Orders order=new Orders();
        String oid= UUID.randomUUID().toString();
        order.setOid(oid);
        order.setOrdertime(new Date());
        Cart cart =(Cart) session.getAttribute("cart");
        order.setTotal(cart.getTotal());
        order.setUsers(user);

        Map<String, CartItem>list=cart.getCartItems();
        for ( Map.Entry<String, CartItem> entry : list.entrySet()) {
            CartItem cartItem = entry.getValue();
            Orderitem orderItem = new Orderitem();
            orderItem.setItemid(UUID.randomUUID().toString());
            orderItem.setCount(cartItem.getBuyNum());
            orderItem.setSubtotal(cartItem.getSubTotal());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setPid(cartItem.getPid());
            orderItem.setOid(oid);
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }
        order.setUid(user.getUid());
        System.out.println(order);
        ordersService.submitOrders(order);
        session.setAttribute("order", order);
        return "order_info";

    }

    @RequestMapping("/confirmOrder")
    public String confirmOrder(HttpSession session,Orders orders)
    {

       Orders order = (Orders) session.getAttribute("order");
        Users user=(Users) session.getAttribute("loginUser");
      order.setAddress(orders.getAddress());
      order.setName(orders.getName());
      order.setTelephone(orders.getTelephone());
      order.setUid(user.getUid());
            ordersService.updateOrderInfo(order);

        return "cart";
    }
    @RequestMapping("myorders")
    public String myorders(HttpSession session,ModelMap modelMap)
    {

        Users user=(Users) session.getAttribute("loginUser");
//        System.out.println(user);
        if (user==null) {

            return  "login";
        }
        List<Orders> orderlist = ordersService.selectOrdersByUid(user.getUid());
//        System.out.println(orderlist);
        if (orderlist!=null)
{
for (Orders orders:orderlist)
{
//    System.out.println(orders);
    List<Map<String, Object>> listMap = ordersService.selectAllOrderItem(orders.getOid());
    for (Map<String, Object> map:listMap)
    {

        System.out.println(map);
        try {
            Orderitem orderitem=new Orderitem();
            BeanUtils.populate(orderitem,map);

            Product product=new Product();
            BeanUtils.populate(product,map);
//            System.out.println(product);
            orderitem.setProduct(product);
            orders.getOrderItems().add(orderitem);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
}
//        System.out.println(orderlist);
modelMap.put("orderlist",orderlist);
        return "order_list";
    }


}



