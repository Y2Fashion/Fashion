package com.accp.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class UserControl {

    @RequestMapping("index")
    public String showInfo(Model m){
        m.addAttribute("user","张三");
        m.addAttribute("count",4);
        m.addAttribute("nowDate",new Date());
        System.out.println("显示首页");
        return "index";
    }
}
