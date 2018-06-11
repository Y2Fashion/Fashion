package com.accp.control;

import com.accp.biz.CommodityBiz;
import com.accp.biz.FirstTypeBiz;
import com.accp.biz.SecondTypeBiz;
import com.accp.biz.ThirdTypeBiz;
import com.accp.entity.FirstType;
import com.accp.entity.SecondType;
import com.accp.entity.ThirdType;
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
import java.util.List;

/***
 * 该控制类用于实验，具体请自己创建
 */
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
    private FirstTypeBiz firstTypeBiz;

    @Resource
    private SecondTypeBiz secondTypeBiz;

    @Resource
    private ThirdTypeBiz thirdTypeBiz;

    @Resource
    private CommodityBiz commodityBiz;

    @Resource
    private OrderBiz orderBiz;

    @Resource
    private StatusBiz statusBiz;


    /**
     * 进入产品管理页面
     * @return
     */
    @RequestMapping("/commodity")
    public String commodity(Model model,Integer type,Integer pageNo){
        FirstType firstType = new FirstType();
        firstType.setfId(0);
        firstType.setFirstType("请选择");
        firstTypeBiz.getList().add(firstType);
        model.addAttribute("firstTypeList",firstTypeBiz.getList());
        model.addAttribute("secondTypeList",secondTypeBiz.getList());
        model.addAttribute("thirdTypeList",thirdTypeBiz.getThirdTypeList(null));
        model.addAttribute("page",commodityBiz.commodityList(type,pageNo));
        return "backstage/commodity";
    }

    /**
     * 进入添加产品页面
     * @return
     */
    @RequestMapping("/commodity_add")
    public String commodity_add(Model model){
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



    /**
     * 进入布料使用情况
     */
    @RequestMapping("/Use")
    public String use(){
        return "backstage/Use";
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
    String name,String phone,String sta,String address,String com) {
        Order order = new Order();
        order.setComment(com);
        order.setClientelePhone(phone);
        order.setClienteleName(name);
        order.setClienteleAddress(address);
        Date c = null;
        Date d = null;
        try {
            c = java.sql.Date.valueOf(crea);
            d = java.sql.Date.valueOf(del);
            order.setCreateTime(c);
            order.setDeliveryTime(d);
        } catch (Exception e) {
            e.getMessage();
        }
        order.setOrderId(id);
        order.setStatus(sta);
        int num = orderBiz.Update(order);
        return "redirect:/order";

    }
    /**
     * 添加布料使用情况
     */
    @RequestMapping("/Use_add")
    public String use_add(){
        return "backstage/Use_add";
    }

    /**
     *布料进货
     */
    @RequestMapping("/Purchase")
    public String purchase(){
        return "backstage/Purchase";
    }

    /**
     * 添加布料进货
     */
    @RequestMapping("/Purchase_add")
    public String purchase_add(){
        return "backstage/Purchase_add";
    }

    /**
     * 查看布料进货订单
     */
    @RequestMapping("/Purchase_get")
    public String purchase_get(){
        return "backstage/Purchase_get";
    }

    /**
     * 修改布料进货订单
     */
    @RequestMapping("/Purchase_upd")
    public String purchase_upd(){
        return "backstage/Purchase_upd";
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
