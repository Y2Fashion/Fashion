package com.accp.control.backstage;

import com.accp.biz.OrderBiz;
import com.accp.biz.StatusBiz;
import com.accp.entity.Order;
import com.accp.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 订单表管理
 */
@Controller
public class OrderController {

    @Resource
    private OrderBiz orderBiz;
    @Resource
    private StatusBiz statusBiz;

    /**
     * 根据时间段查询所有订单
     * @param model
     * @param id 时间段(1:以后 ,2:今天  ,3:以前)
     * @return
     */
    @RequestMapping("main_get")
    public String mian_Get(Model model, String id, HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        Pager<Order> pager=orderBiz.findTime(id,1,2);
        model.addAttribute("mainTime_ID",id);
        model.addAttribute("page",pager);
        return "/backstage/main_get";
    }

    /**
     * 上一页 下一页
     * @param model
     * @param id 时间段(1:以后 ,2:今天  ,3:以前)
     * @param num 第几页
     * @return
     */
    @RequestMapping("main_Get_Pager")
    public String get_Pager(Model model,String id,Integer num,HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        Pager<Order> pager=orderBiz.findTime(id,num,2);
        model.addAttribute("mainTime_ID",id);
        model.addAttribute("page",pager);
        return "/backstage/main_get";
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

        if(cars!=null){
            if(cars=="请选择"||cars.equals("请选择")){
                cars=null;
            }else{
                model.addAttribute("sta",cars);
            }
        }

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
        Order oo=  orderBiz.findById(id);
        String str=null;
        LocalDateTime time=null;
        try{
            if(oo.getCreateTime()!=null){
                str=oo.getCreateTime().substring(0,16);
                DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                time=LocalDateTime.parse(str,df);
                oo.setCreateTime(time.toString());
            }
            if(oo.getDeliveryTime()!=null){
                str=oo.getDeliveryTime().substring(0,16);
                DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                time=LocalDateTime.parse(str,df);
                oo.setDeliveryTime(time.toString());
            }
            model.addAttribute("order",oo);
        }catch (Exception e){

        }
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
        String str=null;
        if(crea!=null && !crea.equals("")){
            str=crea.replace("T"," ");
            crea=str;
        }
        if(del!=null && !del.equals("")){
            str=del.replace("T"," ");
            del=str;
        }
       /* Date c = null;
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");*/
        order.setCreateTime(crea);
        order.setDeliveryTime(del);
        order.setOrderId(id);
        order.setStatus(sta);
        int num = orderBiz.Update(order);
        return null;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            c=sdf.parse(crea);
            d=sdf.parse(del);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-ddHH:mm");
            String t1=sf.format(c);
            String t2=sf.format(d);
            order.setCreateTime(t1);
            order.setDeliveryTime(t2);
        }catch (Exception e){
            e.getMessage();
        }

        order.setStatus(sta);
        int num= orderBiz.Add(order);
        return  null;


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
