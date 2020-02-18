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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class AdminProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value="to_login",method= RequestMethod.GET)
    public String to_login()
    {
//        System.out.println("hhhhhh哎");
        return "index";
    }
    @RequestMapping(value="adminFindAllGoods",method= RequestMethod.GET)
    public ModelAndView adminFindAllGoods()
    {
        ModelAndView mv=new ModelAndView();

        List<Product> list = productService.findAll();
mv.addObject("list",list);
mv.setViewName("/product/list");
        return mv;
    }

    @RequestMapping(value="FindProById",method= RequestMethod.GET )
    public String FindProById(String pid, ModelMap map)
    {


        Product pro = productService.findById(pid);

        List<Category>cateList=categoryService.findAll();
        map.put("pro",pro);
        map.put("cateList", cateList);
        System.out.println(pro);
        System.out.println(cateList);
        return "/product/edit";
    }

    @RequestMapping(value="/deleteproduct",method= RequestMethod.GET )
    public String deleteproduct(String pid )
    {
//        System.out.println(pid);
         productService.delete(pid);


        return "redirect:/adminFindAllGoods";
    }
    @RequestMapping(value="/EditProductServlet",method= RequestMethod.POST )
    public String EditProductServlet( Product product,HttpSession session,HttpServletRequest request, MultipartFile image) throws Exception {
        System.out.println(product);
       //String path="C:\\Users\\11216\\Desktop\\igeekShop_ssm\\igeekshop_web_manager\\src\\main\\webapp\\products";

//        String path = session.getServletContext().getRealPath("/products/");
        String path1="C:\\Users\\11216\\Desktop\\igeekShop_ssm\\igeekshop_web_manager\\src\\main\\webapp\\products";
       String path2="C:\\Users\\11216\\Desktop\\igeekShop_ssm\\igeekshop_web_user\\src\\main\\webapp\\products";
        //        System.out.println(path);

        String fileName = image.getOriginalFilename();
        System.out.println(fileName);
//        File file = new File(path,fileName);
//        System.out.println("图片的路径"+path);
//        if (!file.exists()){
//            file.mkdirs();
//        }

        product.setPimage("products/"+fileName);


        image.transferTo(new File(path1,fileName));
        image.transferTo(new File(path2,fileName));
        productService.update(product);


        return "redirect:/adminFindAllGoods";
    }


    @RequestMapping(value="/ToAddProduct",method= RequestMethod.GET )
    public String ToAddProduct(ModelMap map )
    {
        List<Category> list = categoryService.findAll();
        map.put("list",list);
        return "/product/add";
    }

    @RequestMapping(value="/addproduct",method= RequestMethod.POST )
    public String addproduct(Product product ,HttpSession session,HttpServletRequest request, MultipartFile image) throws Exception { product.setPid(UUID.randomUUID().toString().replace("-", ""));
        System.out.println(product);
//        String path = session.getServletContext().getRealPath("/products/");
        String path1="C:\\Users\\11216\\Desktop\\igeekShop_ssm\\igeekshop_web_manager\\src\\main\\webapp\\products";
        String path2="C:\\Users\\11216\\Desktop\\igeekShop_ssm\\igeekshop_web_user\\src\\main\\webapp\\products";
//        System.out.println(path);

        String fileName = image.getOriginalFilename();
        System.out.println(fileName);
//        File file = new File(path,fileName);
//        System.out.println("图片的路径"+path);
//        if (!file.exists()){
//            file.mkdirs();
//        }

        product.setPimage("products/"+fileName);

//        image.transferTo(new File(path,fileName));
        image.transferTo(new File(path1,fileName));
        image.transferTo(new File(path2,fileName));
      productService.addProduct(product);
        return "/product/add";
    }
}
