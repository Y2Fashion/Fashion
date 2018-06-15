package com.accp.control;

import com.accp.biz.FlowerBiz;
import com.accp.biz.StockBiz;
import com.accp.entity.Colour;
import com.accp.entity.Component;
import com.accp.entity.Flower;
import com.accp.entity.Stock;
import com.accp.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("stock")
public class StockController {
    @Resource
    private StockBiz stockBiz;
    @Resource
    private FlowerBiz flowerBiz;
    /**
     * 进入布料库存
     */
    @RequestMapping("/lining")
    @GetMapping("/")
    public String lining(Model model, Integer pageNo , Integer cmId, Integer clId, Integer fId,
                         String com, String col, String pattern){
        List<Flower> flower=flowerBiz.findFlower();
        model.addAttribute("flower",flower);
        List<Component> components=flowerBiz.findComponent();
        model.addAttribute("components",components);
        List<Colour> colours=flowerBiz.findColour();
        model.addAttribute("colours",colours);
        if(clId!=null&&clId==0){
            clId=null;
        }
        if(cmId!=null&&cmId==0){
            cmId=null;
        }
        if(fId!=null&&fId==0){
            fId=null;
        }
        if(cmId==null){
            if(com!=null){
                cmId=flowerBiz.findCmId(com).getId();
            }
        }
        if(clId==null){
            if(col!=null){
                clId=flowerBiz.findClId(col).getId();
            }
        }
        if(fId==null){
            if(pattern!=null){
                fId=flowerBiz.findFId(pattern).getId();
            }
        }
        Pager<Stock> pager=stockBiz.findStock(pageNo,2,cmId,clId,fId);
        model.addAttribute("pager",pager);
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
    @RequestMapping( "/lining_get")
    @GetMapping("/")
    public String lining_get(Model model,Integer id){
        Stock stock=stockBiz.findId(id);
        model.addAttribute("stock",stock);
        return "backstage/lining_get";
    }

    /**
     * 进入布料库存--修改
     */
    @RequestMapping(value = "/lining_upd",method = RequestMethod.GET)
    @GetMapping("/")
    public String lining_upd(Model model,Integer id){
        Stock stock=stockBiz.findId(id);
        model.addAttribute("stock",stock);
        return "backstage/lining_upd";
    }
    /**
     * 修改布料库存
     */
    @RequestMapping(value = "/lining_upd",method = RequestMethod.POST)
    @GetMapping("/")
    public String lining_upd(Model model,Stock stock){
        stockBiz.update(stock);
        return "redirect:/lining";
    }
}
