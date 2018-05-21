package com.accp.control;

import com.accp.biz.CommodityBiz;
import com.accp.dao.RedisDao;
import com.accp.entity.Commodity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommodityController {

    @Resource
    private CommodityBiz biz;
    @Resource
    private RedisDao redisDao;

    @RequestMapping("/selectCommodityList")
    public String findType(Model model,Integer typeId){
        if(redisDao.exists(typeId.toString())){
            List<Object> list=redisDao.getList(typeId.toString());
            List<Commodity> commodityList=(List<Commodity>)(Object)list;
        }else{
            Commodity commodity=new Commodity();
            commodity.setType(typeId);
            List<Commodity> commodityList=biz.findType(commodity);
            redisDao.addList("id",commodityList);
            model.addAttribute(commodityList);
        }
        return "WAP-BDS-PZ";
    }
    @RequestMapping("/selectCommodity")
    public String findAll(Model model,Integer id){
        Commodity commodity=biz.findId(id);
        model.addAttribute(commodity);
        return null;
    }



    @RequestMapping("/maintenance")
    public String maintenance(){
        return "maintenance";
    }
    @RequestMapping("/Daka")
    public String Daka(){
        return "Daka";
    }
    @RequestMapping("/brandCooper")
    public String brandCooper(){
        return "brandCooper";
    }
    @RequestMapping("/service")
    public String service(){
        return "service";
    }
    @RequestMapping("/yuyue")
    public String yuyue(){
        return "yuyue";
    }
    @RequestMapping("/bigEvent")
    public String bigEvent(){
        return "bigEvent";
    }
    @RequestMapping("/company")
    public String company(){
        return "company";
    }

    /**
     * 新闻动态
     * @return
     */
    @RequestMapping("/media")
    public String media(){
        return "media";
    }
    @RequestMapping("/business")
    public String business(){
        return "business";
    }
    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }
    @RequestMapping("/GW")
    public String GW(){
        return "GW";
    }
    @RequestMapping("/succeed")
    public String succeed(){
        return "succeed";
    }






}
