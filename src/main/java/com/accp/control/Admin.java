package com.accp.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/***
 * 该控制类用于实验，具体请自己创建
 */
@Controller
public class Admin {
    //实验后台页面
    @RequestMapping("/login")
    public String loGin(){
        return "login";
    }

    /*进入后台主页面 /login_go  /head   /left   /main*/
    @RequestMapping("/login_go")
    public String loGin_go(){
        return "/backstage/indexs";
    }

    @RequestMapping("/head")
    public String loGin_head(){
        return "/backstage/head";
    }

    @RequestMapping("/left")
    public String loGin_left(){
        return "/backstage/left";
    }

    @RequestMapping("/main")
    public String loGin_main(){
        return "/backstage/main";
    }

    /**
     * 订单管理页面
     * @return
     */
    @RequestMapping("/order")
    public String loGin_order(Model model){
        model.addAttribute("typeId","1");
        return "/backstage/order";
    }

    /**
     * 进入添加订单页面
     * @return
     */
    @RequestMapping("/order_add")
    public String order_add(){
        return "backstage/order_add";
    }
    /**
     * 进入查询订单页面
     * @return
     */
    @RequestMapping("/order_get")
    public String order_get(){
        System.out.println("aaa");
        return "backstage/order_get";
    }
    /**
     * 进入订单修改页面
     * @return
     */
    @RequestMapping("/order_upd")
    public String order_upd(){
        return "backstage/order_upd";
    }


    /**
     * 进入产品管理页面
     * @return
     */
    @RequestMapping("/commodity")
    public String commodity(){
        return "backstage/commodity";
    }

    /**
     * 进入添加产品页面
     * @return
     */
    @RequestMapping("/commodity_add")
    public String commodity_add(){
        return "backstage/commodity_add";
    }

    /**
     * 进入查看产品页面
     * @return
     */
    @RequestMapping("/commodity_get")
    public String commodity_get(){
        return "backstage/commodity_get";
    }

    /**
     * 查看产品关联图片
     * @return
     */
    @RequestMapping("/commodity_show")
    public String commodity_show(){
        return "backstage/commodity_show";
    }

    /**
     * 进入修改产品页面
     * @return
     */
    @RequestMapping("/commodity_upd")
    public String commodity_upd(){
        return "backstage/commodity_upd";
    }

    /**
     * 进入修改产品关联图片
     * @return
     */
    @RequestMapping("/commodity_updImg")
    public String commodity_updImg(){
        return "backstage/commodity_updImg";
    }
}
