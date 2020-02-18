package com.igeekshop.ssm.controller;


import com.igeekshop.ssm.domain.Category;
import com.igeekshop.ssm.domain.Product;
import com.igeekshop.ssm.service.CategoryService;
import com.igeekshop.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import java.util.List;


@Controller
public class ProductShowController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/showproduct")
    public String showproduct(ModelMap map)
    {
        List<Product> hotProductList = productService.findHotProducts();
        List<Product> NewProductList = productService.findNewProducts();
        map.put("hotProductList", hotProductList);
        map.put("NewProductList", NewProductList);

        return "index";
    }

    @RequestMapping(value = "productList" ,method = RequestMethod.GET)
    public String productList(String cid,String pname,ModelMap map)
    {
        List<Product> products = productService.findByNameAndCid(pname, cid);
        Category category = categoryService.findByid(cid);
        map.put("products",products);
        map.put("category",category);
        return "product_list";
    }

    @RequestMapping("/productinfo")
    public String productinfo(String pid,String cid,ModelMap map)
    {
        Product product = productService.findById(pid);
        Category category = categoryService.findByid(cid);
        map.put("product",product);
        map.put("category",category);
        return "product_info";
    }



}
