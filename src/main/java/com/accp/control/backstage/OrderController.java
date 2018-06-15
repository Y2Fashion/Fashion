package com.accp.control.backstage;

import com.accp.biz.OrderBiz;
import com.accp.entity.Order;
import com.accp.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 订单表管理
 */
@Controller
public class OrderController {

    @Resource
    private OrderBiz orderBiz;

    /**
     * 根据时间段查询所有订单
     * @param model
     * @param id 时间段(1:以后 ,2:今天  ,3:以前)
     * @return
     */
    @RequestMapping("main_get")
    @GetMapping("/")
    public String mian_Get(Model model,String id){
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
    @GetMapping("/")
    public String get_Pager(Model model,String id,Integer num){
        Pager<Order> pager=orderBiz.findTime(id,num,2);
        model.addAttribute("mainTime_ID",id);
        model.addAttribute("page",pager);
        return "/backstage/main_get";
    }

//    /**
//     * 进入
//     * @return
//     */
//    @RequestMapping("ooo_add")
//    public String ooo_Add(){
//        return "/backstage/order_add";
//    }
//
//    /**
//     * 进入
//     * @return
//     */
//    @RequestMapping("ooo_add_add")
//    public String ooo_Add_add(Model model,Order order){
//        Order xx=order;
//        int num= orderBiz.Add(order);
//        return "/backstage/order_add";
//    }
}
