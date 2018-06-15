package com.accp.control;

import com.accp.biz.CommodityBiz;
import com.accp.biz.FirstTypeBiz;
import com.accp.biz.SecondTypeBiz;
import com.accp.biz.ThirdTypeBiz;
import com.accp.entity.FirstType;

import com.accp.entity.ThirdType;
import com.accp.biz.OrderBiz;
import com.accp.biz.StatusBiz;
import com.accp.entity.Order;
import com.accp.util.Pager;

import com.accp.biz.*;
import com.accp.entity.*;
import com.accp.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/***
 * 该控制类用于实验，具体请自己创建
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;


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
    private UserOrderBiz userOrderBiz;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private StatusBiz statusBiz;

    @Resource
    private OrderBiz orderBiz;



    /**
     * 进入产品管理页面
     * @return
     */
    @RequestMapping("/commodity")
    @GetMapping("/")
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
     * 进入布料库存
     */
    @RequestMapping("/lining")
    public String lining(){
        return "backstage/lining";
    }

    /**
     * 进入布料库存--添加
     */
    @RequestMapping("/lining_add")
    public String lining_add(){
        return "backstage/lining_add";
    }

    /**
     * 进入布料库存--查看
     */
    @RequestMapping("/lining_get")
    public String lining_get(){
        return "backstage/lining_get";
    }

    /**
     * 进入布料库存--修改
     */
    @RequestMapping("/lining_upd")
    public String lining_upd(){
        return "backstage/lining_upd";
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
    @GetMapping("/")
    public String order(Model model ,Integer pageNo,String cars){
        Pager<Order> pager=orderBiz.findAll(cars,pageNo,2);
        model.addAttribute("pages",pager);
        model.addAttribute("status",statusBiz.getAll());
        return "/backstage/order";
    }
    @RequestMapping("/order_get")
    @GetMapping("/")
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
    @GetMapping("/")
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
    @GetMapping("/")
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
    @GetMapping("/")
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
    @GetMapping("/")
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
    @GetMapping("/")
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
    @GetMapping("/")
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
    /*
     * 柱形算图
     * */
    @RequestMapping("/CommodityTopView")
    @GetMapping("/")
    public String goZView(Model model){

        List<String>dataName=new ArrayList<String>();
        List<Integer> datas=new ArrayList<Integer>();
        JSON dataNameJ=null;
        JSON datasJ=null;
        redisUtil.remove("dataNameJ");
        if(redisUtil.exists("dataNameJ")){
            dataNameJ=(JSON)redisUtil.lRange("dataNameJ",0,redisUtil.length("dataNameJ")).get(0);
            datasJ=(JSON)redisUtil.lRange("datasJ",0,redisUtil.length("datasJ")).get(0);
        }else{
            List<UserOrder> UserOrders=userOrderBiz.getCommdityTop10();
            if(UserOrders.size()>0){
                for (int i=UserOrders.size()-1;i>=0;i--) {
                    UserOrders.get(i).setCommodity(commodityBiz.getCommodityById(UserOrders.get(i).getCommodityId()));
                    dataName.add(UserOrders.get(i).getCommodity().getCommodityName());
                    datas.add(UserOrders.get(i).getCountCID());
                }
                dataNameJ=(JSON)JSON.toJSON(dataName);
                datasJ=(JSON)JSON.toJSON(datas);
            }
            redisUtil.lPush("dataNameJ",dataNameJ);
            redisUtil.lPush("datasJ",datasJ);
        }
        model.addAttribute("dataName",dataNameJ);
        model.addAttribute("datas",datasJ);
        return "backstage/CommodityTopView";
    }

    @RequestMapping("/ThreeTypeView")
    @GetMapping("/")
    public String goThreeTypeView(Model model){

        List<String> typeName=new ArrayList<String>();
        List<view> views=new ArrayList<view>();
        JSON view=null;
        JSON typeNames=null;
        if(redisUtil.exists("view")){
            view=(JSON)redisUtil.lRange("view",0,redisUtil.length("view")).get(0);
            typeNames=(JSON)redisUtil.lRange("typeNames",0,redisUtil.length("typeNames")).get(0);
        }else{
            List<Commodity> commodities=commodityBiz.getHitsGroupType();
            List<ThirdType> thirdTypes=thirdTypeBiz.getThirdTypeByCArray(commodities);
            for (ThirdType t:thirdTypes) {
                if(t.getsId()<7){
                    t.setThirdType("男式"+t.getThirdType());
                }else if(t.getsId()>=8){
                    t.setThirdType("女式"+t.getThirdType());
                }
                typeName.add(t.getThirdType());
            }
            for(int i=0;i<commodities.size();i++){
                views.add(new view(thirdTypes.get(i).getThirdType(),commodities.get(i).getHits()));
            }
            view=(JSON)JSON.toJSON(views);
            typeNames=(JSON)JSON.toJSON(typeName);
        }

        model.addAttribute("view",view);
        model.addAttribute("typeName",typeNames);
        return "backstage/ThreeTypeView";
    }

    @RequestMapping("/lineView")
    @GetMapping("/")
    private String goToLineView(Model model){
        List<UserOrder> userOrders=new ArrayList<UserOrder>();
        List<String> dataName=new ArrayList<String>();
        List<ThirdType> thirdTypes=new ArrayList<ThirdType>();
        Integer[][] userOrderLists=null;
        JSON dataNameJ=null;
        JSON datasJ=null;
        if(redisUtil.exists("lineDataName")){
            dataNameJ=(JSON)redisUtil.lRange("lineDataName",0,redisUtil.length("lineDataName")).get(0);
            datasJ=(JSON)redisUtil.lRange("lineDatas",0,redisUtil.length("lineDatas")).get(0);
        }else{
            userOrders=userOrderBiz.getThreeTypes();
            thirdTypes=thirdTypeBiz.getThirdName(userOrders);
            userOrderLists=new Integer[userOrders.size()][];
            for (ThirdType t : thirdTypes) {
                if(t.getsId()>=8){
                    dataName.add("女式"+t.getThirdType());
                }else if(t.getsId()<7){
                    dataName.add("男式"+t.getThirdType());
                }
            }
            int b=0;
            for (UserOrder userOrder:userOrders) {
                List<UserOrder> userOrders1=userOrderBiz.getSaleByMonth(userOrder.getThreeTypeId());
                Integer[] ints=new Integer[12];
                for (UserOrder u:userOrders1) {
                    for(int j=1;j<=12;j++){
                        if(u.getTime()==j){
                            ints[j-1]=u.getCountCID();
                        }else{
                            if(ints[j-1]==null){
                                ints[j-1]=0;
                            }
                        }
                    }
                }
                userOrderLists[b]=ints;
                b++;
            }
            dataNameJ=(JSON)JSON.toJSON(dataName);
            datasJ=(JSON)JSON.toJSON(userOrderLists);
            redisUtil.lPush("lineDataName",dataNameJ);
            redisUtil.lPush("lineDatas",datasJ);
        }

        model.addAttribute("dataName",dataNameJ);
        model.addAttribute("datas",datasJ);
        return "backstage/lineView";
    }

    @RequestMapping("BazaarView")
    @GetMapping("/")
    public String goVazaarView(Model model){
        List<UserOrder> userOrders=new ArrayList<UserOrder>();
        List<String> dataName=new ArrayList<String>();
        Integer[] datas=null;
        JSON dataNameJ=null;
        JSON datasJ=null;
        redisUtil.remove("BazaarDataName");
        redisUtil.remove("BazaarDatas");
        redisUtil.remove("BazaarDataName");
        if(redisUtil.exists("BazaarDataName")){
            dataNameJ=(JSON)redisUtil.lRange("BazaarDataName",0,redisUtil.length("BazaarDataName")).get(0);
            datasJ=(JSON)redisUtil.lRange("BazaarDatas",0,redisUtil.length("BazaarDatas")).get(0);
        }else{
            userOrders=userOrderBiz.getSaleByAddress();
            datas=new Integer[userOrders.size()];
            int i=0;
            for (UserOrder u:userOrders) {
                datas[i]=u.getCountCID();
                String name=u.getAddress();
                if(name.indexOf("上海")!=-1||name.indexOf("北京")!=-1||name.indexOf("天津")!=-1||name.indexOf("香港")!=-1||name.indexOf("澳门")!=-1||name.indexOf("广西")!=-1||name.indexOf("西藏")!=-1||name.indexOf("宁夏")!=-1||name.indexOf("新疆")!=-1){
                    name=name.substring(0,2);
                }
                if(name.indexOf("内蒙古")!=-1||name.indexOf("黑龙江")!=-1){
                    name=name.substring(0,3);
                }
                dataName.add(name);
                i++;
            }
            dataNameJ=(JSON)JSON.toJSON(dataName);
            datasJ=(JSON)JSON.toJSON(datas);
            redisUtil.lPush("BazaarDataName",dataNameJ);
            redisUtil.lPush("BazaarDatas",datasJ);
        }
        model.addAttribute("dataName",dataNameJ);
        model.addAttribute("datas",datasJ);
        return "backstage/BazaarView";
    }

}
