package com.accp.control;

import com.accp.biz.OrderBiz;
import com.accp.biz.StatusBiz;
import com.accp.entity.Order;
import com.accp.util.Pager;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class Admin {
    @Resource
    private StatusBiz statusBiz;
    @Resource
    private OrderBiz orderBiz;
    //实验后台页面
    @RequestMapping("/login")
    public String loGin(){
        return "/backstage/index";
    }
    @RequestMapping("/head")
    public String head(){
        return "/backstage/head";
    }
    @RequestMapping("/left")
    public String left(){
        return "/backstage/left";
    }
    @RequestMapping("/main")
    public String main(){
        return "/backstage/main";
    }

    /**
     * 模糊查询及分页
     * @param model
     * @param pageNo
     * @param cars
     * @return
     */
    @RequestMapping("/order")
    public String order(Model model ,Integer pageNo,String cars){
        Pager<Order> pager=orderBiz.findAll(cars,pageNo,2);
        model.addAttribute("pages",pager);
        model.addAttribute("status",statusBiz.getAll());
        return "/backstage/order";
    }
    @RequestMapping("/order_get")
    public String orderget(Model model,Integer id){
        model.addAttribute("order",orderBiz.findById(id));
        return "/backstage/order_get";
    }

    /**
     * 跳修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/order_upd",method=RequestMethod.GET)
    public String orderupd(Model model,Integer id){
        model.addAttribute("order",orderBiz.findById(id));
        return "/backstage/order_upd";
    }
    /*
     * 修改
     * @param model
     * @param id
     * @param crea
     * @param del
     * @param name
     * @param phone
     * @param sta
     * @param address
     * @param com
     * @return
     */
    @RequestMapping(value = "/order_upd",method=RequestMethod.POST)
    public String update(Model model,Integer id,String crea,String del,
    String name,String phone,String sta,String address,String com){
        Order order=new Order();
        order.setComment(com);
        order.setClientelePhone(phone);
        order.setClienteleName(name);
        order.setClienteleAddress(address);
        Date c=null;
        Date d=null;
        try{
            c=java.sql.Date.valueOf(crea);
            d=java.sql.Date.valueOf(del);
            order.setCreateTime(c);
            order.setDeliveryTime(d);
        }catch (Exception e){
            e.getMessage();
        }
        order.setOrderId(id);
        order.setStatus(sta);
       int num= orderBiz.Update(order);
       return  "redirect:/order";


    }

    /**
     * 跳增加页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/order_add",method=RequestMethod.GET)
    public String orderadd(Model model) {
        return "/backstage/order_add";
    }

    /**
     * 增加
     * @param model
     * @param crea
     * @param del
     * @param name
     * @param phone
     * @param sta
     * @param address
     * @param com
     * @return
     */
    @RequestMapping(value = "/order_add",method=RequestMethod.POST)
    public String orderadd(Model model,String crea,String del,
                         String name,String phone,String sta,String address,String com){
        Order order=new Order();
        order.setComment(com);
        order.setClientelePhone(phone);
        order.setClienteleName(name);
        order.setClienteleAddress(address);
        Date c=null;
        Date d=null;
        try{
            c=java.sql.Date.valueOf(crea);
            d=java.sql.Date.valueOf(del);

        }catch (Exception e){
            e.getMessage();
        }
        order.setCreateTime(c);
        order.setDeliveryTime(d);

        order.setStatus(sta);
        int num= orderBiz.Add(order);
        return  "redirect:/order";


    }

    /**
     * 单个删除
     * @param orderId
     * @return
     */
    @RequestMapping("/del")
    public String del(Integer orderId){
        orderBiz.Del(orderId);

        return "redirect:/order";
    }

    /**
     * 批量删除
     * @param arr
     * @return
     */
    @RequestMapping("/dels")
    @ResponseBody
    public String dels(@RequestParam(value = "arr[]") Integer[] arr){
        List<Integer> a= Arrays.asList(arr);
        for(Integer i:a){
            if(i!=null){
                //System.out.println(i);
                orderBiz.Del(i);
            }

        }
        return "redirect:/order";
    }
}
