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


}
