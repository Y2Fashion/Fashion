package com.accp.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Admin {
    //实验后台页面
    @RequestMapping("/login")
    public String loGin(){
        return "backstage/indexs";
    }

    @RequestMapping("/login_go")
    public String loGin_go(){
        return "/backstage/index";
    }

}
