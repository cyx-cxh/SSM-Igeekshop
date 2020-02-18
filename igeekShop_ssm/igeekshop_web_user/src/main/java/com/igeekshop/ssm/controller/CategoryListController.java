package com.igeekshop.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.igeekshop.ssm.domain.Category;
import com.igeekshop.ssm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryListController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping ("/categoryList")
    public String categoryList(ModelMap map)
    {
        System.out.println("哈哈");
        List<Category> all = categoryService.findAll();
        String categoryListJson = JSON.toJSONString(all);
        System.out.println(categoryListJson);

        return categoryListJson;
    }



}
