package com.accp.control;

import com.accp.biz.CommodityBiz;
import com.accp.entity.Commodity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommodityController {

    @Resource
    private CommodityBiz biz;
    @RequestMapping("/selectCommodity")
    public String findType(Model model,Integer type){
        Commodity commodity=new Commodity();
        commodity.setType(type);
        List<Commodity> list=biz.findType(commodity);
        model.addAttribute(list);
        return "WAP-BDS-PZ";
    }
    @RequestMapping("/")
    public String findAll(Model model,Integer id){
        Commodity commodity=biz.findId(id);
        model.addAttribute(commodity);
        return null;
    }
}
