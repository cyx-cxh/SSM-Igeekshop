package com.igeekshop.ssm.controller;

import com.igeekshop.ssm.domain.Product;
import com.igeekshop.ssm.domain.Users;
import com.igeekshop.ssm.service.ProductService;
import com.igeekshop.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Controller

public class UserLoginAndRegistContorller {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @RequestMapping("to_userlogin")
        public String  to_login()
        {
            return "login";
        }

    @RequestMapping("checkcode")
    public void   checkcode(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        // gui 生成图片
        // 1 高和宽
        int height = 30;
        int width = 60;
        String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        // 2 创建一个图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 3 获得画板
        Graphics g = image.getGraphics();
        // 4 填充一个矩形
        // * 设置颜色
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.fillRect(1, 1, width - 2, height - 2);
        // * 设置字体
        g.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 25));
        StringBuilder sb = new StringBuilder();// 获取验证码字符信息
        // 5 写随机字
        for (int i = 0; i < 4; i++) {
            // 设置颜色--随机数
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

            // 获得随机字
            int index = random.nextInt(data.length());
            String str = data.substring(index, index + 1);
            sb.append(str);
            // 写入
            g.drawString(str, width / 6 * (i + 1), 20);

        }
        // 验证码保存到session对象中
        request.getSession().setAttribute("checkcode1", sb.toString());

        // 6 干扰线
        for (int i = 0; i < 3; i++) {
            // 设置颜色--随机数
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            // 随机绘制先
            g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
            // 随机点
            g.drawOval(random.nextInt(width), random.nextInt(height), 2, 2);
        }

        // end 将图片响应给浏览器
        ImageIO.write(image, "jpg", response.getOutputStream());

    }
    @RequestMapping("userlogin" )
    public String  login(Users user,String checkcode, HttpSession checkcode1,String remember,ModelMap map,HttpServletRequest request,HttpServletResponse response)
    {
        System.out.println("session的值为"+(String) checkcode1.getAttribute("checkcode1"));
        System.out.println("输入的验证码为"+checkcode);
        String yzm = (String) checkcode1.getAttribute("checkcode1");
        if (!checkcode.equalsIgnoreCase(yzm)) {

            map.put("msg","验证码错误");

            return"login";
        }
        Users u = userService.login(user);
if (u==null)
    {
        map.put("msg", "用户名或者密码错误");
        return "login";
    }
else {


    System.out.println(remember);
    if ("yes".equals(remember)) {
        Cookie cookie=new Cookie("username",u.getEmail());
        cookie.setMaxAge(60*60*24*7);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    else {
        Cookie cookie=new Cookie("username","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    request.getSession().setAttribute("loginUser", u);

    return "forward:/showproduct";
}
    }
    @RequestMapping("UserloginOut" )
    public  String  loginOut(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        Cookie cookname = new Cookie("cookname", "");
        cookname.setMaxAge(0);
        response.addCookie(cookname);
        return "login";
    }

    @RequestMapping(value = "userRegist" ,method= RequestMethod.POST )
    public  String  userRegist(Users users)
    {
        users.setUid(UUID.randomUUID().toString());
        //code激活码，使用UUID生成
        users.setCode(UUID.randomUUID().toString());
        System.out.println(users);
        userService.regist(users);
        return "login";
    }

}
