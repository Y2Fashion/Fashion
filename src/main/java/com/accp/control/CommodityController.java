package com.accp.control;

import com.accp.biz.CommodityBiz;
import com.accp.entity.Commodity;
import com.accp.util.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommodityController {

    @Resource
    private  CommodityBiz biz;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private CommodityBiz biz;

    @RequestMapping("/selectCommodityList")
    public String findType(Model model,Integer typeId) {
        List<Commodity> commodityList = new ArrayList<Commodity>();
        String key = typeId + "产品";
        if (redisUtil.exists(key)) {
            commodityList = (List<Commodity>) redisUtil.lRange(key, 0, redisUtil.length(key)).get(0);
        } else {
            Commodity commodity = new Commodity();
            commodity.setType(typeId);
            commodityList = biz.findType(commodity);
            redisUtil.lPush(key, commodityList);
        }
        model.addAttribute(commodityList);
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
            commodityList=biz.findType(commodity);
            redisUtil.lPush(key,commodityList);
        }
        return commodityList;
    }

        @RequestMapping("/selectCommodity")
        public String findAll(Model model,Integer id){
            Commodity commodity=biz.findId(id);
            model.addAttribute(commodity);
            return null;
        }


}
