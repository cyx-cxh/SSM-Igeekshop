package com.igeekshop.ssm.controller;

import com.igeekshop.ssm.domain.Category;

import com.igeekshop.ssm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value="/findAllCategory",method= RequestMethod.GET)
    public ModelAndView findAllCategory()
    {
        ModelAndView mv=new ModelAndView();

        List<Category> list = categoryService.findAll();
        mv.addObject("list",list);
        mv.setViewName("/category/list");
        return mv;
    }

    @RequestMapping(value="/deleteCategoryByid",method= RequestMethod.GET )
    public String deleteCategoryByid(String cid )
    {
        System.out.println(cid);
        categoryService.delete(cid);
        return "forward:/findAllCategory";
    }

    @RequestMapping(value="/findCategoryById",method= RequestMethod.GET )
    public String findCategoryById(String cid,ModelMap map)
    {
        System.out.println(cid);
        Category category = categoryService.findByid(cid);
        map.put("category",category);
        return "/category/edit";
    }

    @RequestMapping(value="/updateCategory",method = RequestMethod.POST)
    public String updateCategory(Category category )
    {
        System.out.println(category);
        categoryService.update(category);

        return "redirect:/findAllCategory";
    }

    @RequestMapping(value="/addCategory",method= RequestMethod.POST )
    public String addCategory(Category category )
    {
        category.setCid(UUID.randomUUID().toString().replace("-", ""));
        System.out.println(category);
        categoryService.add(category);
        return "/category/add";
    }

}
