package com.accp.control;

import com.accp.biz.*;
import com.accp.entity.*;
import com.accp.util.Iputil;
import com.accp.util.RedisUtil;
import com.accp.util.Storage;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class CommodityController {

    @Resource
    private  CommodityBiz biz;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private CommodityBiz commodityBiz;
    @Resource
    private ThirdTypeBiz thirdTypeBiz;
    @Resource
    private LiNingBiz liNingBiz;
    @Resource
    private AccessingDataBiz accessingDataBiz;

    //按三极分类类型查询商品
    @RequestMapping("/selectCommodityList")
    public String findType(HttpServletRequest request,Model model,Integer typeId) {
        //添加用户记录
        String IP=Iputil.getIpAddr(request);
        if(Storage.accessingData.size()>0){
            List<AccessingData> accessingDatas=Storage.accessingData;
            int a=0;
            for (int i=0;i<accessingDatas.size();i++) {
               if(accessingDatas.get(i).getTypeID()!=null&&accessingDatas.get(i).getTypeID().toString().equals(typeId.toString())){
                    accessingDatas.get(i).setLookCount(accessingDatas.get(i).getLookCount()+1);
                    a++;
                }
            }
            if(a==0){
                accessingDatas.add(new AccessingData(IP,0,0,1,typeId));
            }
            Storage.accessingData=accessingDatas;
        }else{
            List<AccessingData> accessingData=new ArrayList<AccessingData>();
            accessingData.add(new AccessingData(IP,0,0,1,typeId));
            Storage.accessingData=accessingData;
        }

        //查询商品
        List<Commodity> commodityList = new ArrayList<Commodity>();
        String key = typeId + "产品";
        if (redisUtil.exists(key)) {
            commodityList = (List<Commodity>) redisUtil.lRange(key, 0, redisUtil.length(key)).get(0);
        } else {
            Commodity commodity = new Commodity();
            commodity.setType(typeId);
            commodityList = biz.findType(commodity);
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

    //ajax按三极分类类型查询商品
    @RequestMapping("/ajaxCommodityList")
    @ResponseBody
    public List<Commodity> ajaxCommodityList(HttpServletRequest request,Integer typeId){

        //添加用户操作记录
        String IP=Iputil.getIpAddr(request);
        if(Storage.accessingData.size()>0){
            List<AccessingData> accessingDatas= new ArrayList<AccessingData>();
            accessingDatas=Storage.accessingData;
            int a=0;
            for (int i=0;i<accessingDatas.size();i++) {
                if(accessingDatas.get(i).getTypeID()!=null&&accessingDatas.get(i).getTypeID().toString().equals(typeId.toString())){
                    accessingDatas.get(i).setLookCount(accessingDatas.get(i).getLookCount()+1);
                    a++;
                }
            }
            if(a==0){
                accessingDatas.add(new AccessingData(IP,0,0,1,typeId));
            }

            Storage.accessingData=accessingDatas;
        }else{
            List<AccessingData> accessingData=new ArrayList<AccessingData>();
            accessingData.add(new AccessingData(IP,0,0,1,typeId));
            Storage.accessingData=accessingData;
        }

        List<Commodity> commodityList=new ArrayList<Commodity>();
        String key=typeId+"产品";
        if(redisUtil.exists(key)){
            commodityList=(List<Commodity>) redisUtil.lRange(key,0,redisUtil.length(key)).get(0);
        }else{
            Commodity commodity=new Commodity();
            commodity.setType(typeId);
            commodityList=biz.findType(commodity);
            redisUtil.lPush(key,commodityList);
        }
        return commodityList;
    }

    // 首页
    @RequestMapping("/index")
    public String goToIndex(Model model, HttpServletRequest request){

        /*获取访问者IP*/
        String IP=Iputil.getIpAddr(request);
        List<AccessingData> accessingDatas=accessingDataBiz.getAccessingDataList(IP);
        List<AccessingData> accessingDatas2=new ArrayList<AccessingData>();
        if(accessingDatas!=null){
            Storage.accessingData=accessingDatas;
            //获取访问者达到要求的商品
            if(redisUtil.exists("interestList")){
                List<Commodity> commodities=(List<Commodity>)redisUtil.lRange("interestList",0,redisUtil.length("interestList")).get(0);
                model.addAttribute("interestList",commodities);
                model.addAttribute("listSize",commodities.size());
                model.addAttribute("bool",1);
            }else{
                for (AccessingData ad :accessingDatas) {
                    if(ad.getcId()!=null&&ad.getcId()>0&&(ad.getLookCount()>=3||ad.getLookTime()>=180)){
                        accessingDatas2.add(ad);
                    }
                    if(accessingDatas2.size()>0){
                        List<Commodity> commodities=commodityBiz.getCommListByXQArray(accessingDatas2);
                        for (Commodity c : commodities) {
                            c.setOriginalPrice(c.getVipPrice()-100);
                        }
                        model.addAttribute("interestList",commodities);
                        redisUtil.lPush("interestList",commodities);
                        model.addAttribute("bool",1);
                    }
                }
            }
            //获取用户的历史访问记录
            List<AccessingData> accessingDataTID=accessingDataBiz.getAccessingDataListBool(IP,true);
            List<AccessingData> accessingDataCID=accessingDataBiz.getAccessingDataListBool(IP,false);
            if(accessingDataTID!=null){
                List<ThirdType> thirdTypes=thirdTypeBiz.getThirdTypeByArray(accessingDataTID);
                model.addAttribute("thirdTypeList",thirdTypes);
            }
            if(accessingDataCID!=null){
                List<Commodity> commodityList=biz.getCommodityListByArray(accessingDataCID);
                model.addAttribute("commodityListXQ",commodityList);
            }
        }
        redisUtil.lPush("userIP", IP);

        //获取各类型商品 按热度查询
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

    //按二级分类查询以热度排序
    @RequestMapping("getCommodityListBySType")
    public String getCommodityListBySType(Model model,String SecondTypeId){
        model.addAttribute("commodityList",biz.getCommoditys(SecondTypeId));
        return "WAP-BDS-PZ";
    };

    //衣服详细页面
    @RequestMapping("/selectCommodity")
    public String goToXiangXi(Model model,Integer id,HttpServletRequest request) {
        /*获取进入商品时间*/
        Date time=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        request.getSession().setAttribute(id.toString(),df.format(time));

        Commodity commodity=biz.findId(id);
        Lining lining=liNingBiz.getLiNingById(commodity.getlId());
        List<Lining> LiNingList=liNingBiz.getLiNingList();
        List<Commodity> commodity1=biz.getCommoditys(commodity.getType());
        List<Commodity> commoditys=new ArrayList<Commodity>();
        int i=0;
        for (Commodity c:commodity1) {
            i++;
            if(i<5){
                commoditys.add(c);
            }
        }

        model.addAttribute("lining",lining);
        model.addAttribute("commodity",commodity);
        model.addAttribute("LiNingList",LiNingList);
        model.addAttribute("commoditys",commoditys);
        return "WAP-BDS-PZ-132";
    }


}
