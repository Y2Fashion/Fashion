package com.accp.control;

import com.accp.biz.*;
import com.accp.entity.Commodity;
import com.accp.entity.Lining;
import com.accp.entity.SecondType;
import com.accp.entity.ThirdType;
import com.accp.util.Iputil;
import com.accp.util.RedisUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CommodityController {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private CommodityBiz commodityBiz;
    @Resource
    private ThirdTypeBiz thirdTypeBiz;
    @Resource
    private LiNingBiz liNingBiz;


    @RequestMapping("/selectCommodityList")
    public String findType(Model model,Integer typeId) {
        List<Commodity> commodityList = new ArrayList<Commodity>();
        String key = typeId + "产品";
        if (redisUtil.exists(key)) {
            commodityList = (List<Commodity>) redisUtil.lRange(key, 0, redisUtil.length(key)).get(0);
        } else {
            Commodity commodity = new Commodity();
            commodity.setType(typeId);
            commodityList = commodityBiz.findType(commodity);
            for (Commodity c :commodityList) {
                c.setLining(liNingBiz.getLiNingById(c.getlId()));
            }
            redisUtil.lPush(key, commodityList);
        }

        model.addAttribute("commodityList",commodityList);
        model.addAttribute("CName",thirdTypeBiz.getThirdType(typeId).getThirdType());
        model.addAttribute("typeId",typeId);
        return "WAP-BDS-PZ";
    }
    @RequestMapping("/ajaxCommodityList")
    @ResponseBody
    public List<Commodity> ajaxCommodityList(Integer typeId){
        List<Commodity> commodityList=new ArrayList<Commodity>();
        String key=typeId+"产品";
        if(redisUtil.exists(key)){
            commodityList=(List<Commodity>) redisUtil.lRange(key,0,redisUtil.length(key)).get(0);
        }else{
            Commodity commodity=new Commodity();
            commodity.setType(typeId);
            commodityList=commodityBiz.findType(commodity);
            redisUtil.lPush(key,commodityList);
        }
        return commodityList;
    }

    // 首页
    @RequestMapping("/index")
    public String goToIndex(Model model, HttpServletRequest request){

        String IP=Iputil.getIpAddr(request);

        List<Commodity> xiZhuangHitsList=null;
        List<Commodity> chenSanHitsList=null;
        List<Commodity> kuZhuangHitsList=null;
        List<Commodity> waiTaoHitsList=null;
        List<Commodity> niuZaiKuHitsList=null;
        List<Commodity> nvShiHitsList=null;
        if(redisUtil.exists("xiZhuangHitsList")){
            xiZhuangHitsList=(List<Commodity>)redisUtil.lRange("xiZhuangHitsList",0,redisUtil.length("xiZhuangHitsList")).get(0);
            chenSanHitsList=(List<Commodity>)redisUtil.lRange("chenSanHitsList",0,redisUtil.length("chenSanHitsList")).get(0);
            kuZhuangHitsList=(List<Commodity>)redisUtil.lRange("kuZhuangHitsList",0,redisUtil.length("kuZhuangHitsList")).get(0);
            waiTaoHitsList=(List<Commodity>)redisUtil.lRange("waiTaoHitsList",0,redisUtil.length("waiTaoHitsList")).get(0);
            niuZaiKuHitsList=(List<Commodity>)redisUtil.lRange("niuZaiKuHitsList",0,redisUtil.length("niuZaiKuHitsList")).get(0);
            nvShiHitsList=(List<Commodity>)redisUtil.lRange("nvShiHitsList",0,redisUtil.length("nvShiHitsList")).get(0);
        }else{
            xiZhuangHitsList=commodityBiz.getCommoditys("1");
            chenSanHitsList=commodityBiz.getCommoditys("3");
            kuZhuangHitsList=commodityBiz.getCommoditys("4");
            waiTaoHitsList=commodityBiz.getCommoditys("6");
            niuZaiKuHitsList=commodityBiz.getCommoditys(32);
            nvShiHitsList=commodityBiz.getCommoditys(true);
            redisUtil.lPush("xiZhuangHitsList",xiZhuangHitsList);
            redisUtil.lPush("chenSanHitsList",chenSanHitsList);
            redisUtil.lPush("kuZhuangHitsList",kuZhuangHitsList);
            redisUtil.lPush("waiTaoHitsList",waiTaoHitsList);
            redisUtil.lPush("niuZaiKuHitsList",niuZaiKuHitsList);
            redisUtil.lPush("nvShiHitsList",nvShiHitsList);
        }
        model.addAttribute("xiZhuangHitsList",xiZhuangHitsList);
        model.addAttribute("chenSanHitsList",chenSanHitsList);
        model.addAttribute("kuZhuangHitsList",kuZhuangHitsList);
        model.addAttribute("waiTaoHitsList",waiTaoHitsList);
        model.addAttribute("niuZaiKuHitsList",niuZaiKuHitsList);
        model.addAttribute("nvShiHitsList",nvShiHitsList);
        return "index";
    }

    @RequestMapping("getCommodityListBySType")
    public String getCommodityListBySType(Model model,String SecondTypeId){
        model.addAttribute("commodityList",commodityBiz.getCommoditys(SecondTypeId));
        return "WAP-BDS-PZ";
    };

    //衣服详细页面
    @RequestMapping("/selectCommodity")
    public String goToXiangXi(Model model,Integer id,HttpServletRequest request) {
        Date time=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        request.getSession().setAttribute(id.toString(),df.format(time));
        Commodity commodity=commodityBiz.findId(id);
        Lining lining=liNingBiz.getLiNingById(commodity.getlId());
        List<Lining> LiNingList=liNingBiz.getLiNingList();
        List<Commodity> commodity1=commodityBiz.getCommoditys(commodity.getType());
        List<Commodity> commoditys=new ArrayList<Commodity>();
        commoditys.add(commodity1.get(0));
        commoditys.add(commodity1.get(1));
        commoditys.add(commodity1.get(2));
        commoditys.add(commodity1.get(3));
        model.addAttribute("lining",lining);
        model.addAttribute("commodity",commodity);
        model.addAttribute("LiNingList",LiNingList);
        model.addAttribute("commoditys",commoditys);
        return "WAP-BDS-PZ-132";
    }

    @RequestMapping("monitor")
    @ResponseBody
    private void monitor(String cId,HttpServletRequest request){
        DateFormat dfsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date orderTime=new Date();
       // Date lookTime=new Date();
        Date enterTime=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            enterTime=dfsf.parse((String) request.getSession().getAttribute("cId"));
            orderTime=dfsf.parse(df.format(orderTime));

        }catch (Exception e){

        }
        long lookTime=enterTime.getTime()-orderTime.getTime();
        long time=lookTime/1000;
    }
}
