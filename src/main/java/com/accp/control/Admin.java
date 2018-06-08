package com.accp.control;

import com.accp.biz.*;
import com.accp.entity.*;
import com.accp.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.*;

/***
 * 该控制类用于实验，具体请自己创建
 */
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
     * 供应商渠道
     */
    @RequestMapping("/Channel")
    public String channel(){
        return "backstage/Channel";
    }

    /**
     * 添加供应商渠道
     */
    @RequestMapping("/Channel_add")
    public String channel_upd(){
        return "backstage/Channel_add";
    }

    /**
     *供应商
     */
    @RequestMapping("/Supplier")
    public String supplier(){
        return "backstage/Supplier";
    }

    /**
     * 添加供应商
     */
    @RequestMapping("/Supplier_add")
    public String supplier_add(){
        return "backstage/Supplier_add";
    }

    /**
     * 查看供应商
     */
    @RequestMapping("/Supplier_get")
    public String supplier_get(){
        return "backstage/Supplier_get";
    }

    /**
     * 修改供应商
     */
    @RequestMapping("/Supplier_upd")
    public String supplier_upd(){
        return "backstage/Supplier_upd";
    }

    /*
    * 柱形算图
    * */
    @RequestMapping("/CommodityTopView")
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
    public String goVazaarView(Model model){
        List<UserOrder> userOrders=new ArrayList<UserOrder>();
        List<String> dataName=new ArrayList<String>();
        Integer[] datas=null;
        JSON dataNameJ=null;
        JSON datasJ=null;
        redisUtil.remove("BazaarDataName");
        redisUtil.remove("BazaarDatas");
        if(redisUtil.exists("BazaarDataName")){
            dataNameJ=(JSON)redisUtil.lRange("BazaarDataName",0,redisUtil.length("BazaarDataName")).get(0);
            datasJ=(JSON)redisUtil.lRange("BazaarDatas",0,redisUtil.length("BazaarDatas")).get(0);
        }else{
            userOrders=userOrderBiz.getSaleByAddress();
            datas=new Integer[userOrders.size()];
            int i=0;
            for (UserOrder u:userOrders) {
                datas[i]=u.getCountCID();
                dataName.add(u.getAddress());
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
