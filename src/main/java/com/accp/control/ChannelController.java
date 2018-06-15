package com.accp.control;

import com.accp.biz.ChannelBiz;
import com.accp.biz.FlowerBiz;
import com.accp.biz.SupplierBiz;
import com.accp.entity.*;
import com.accp.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 供货渠道管理
 */
@Controller
public class ChannelController {
    @Resource
    private FlowerBiz flowerBiz;
    @Resource
    private ChannelBiz channelBiz;
    @Resource
    private SupplierBiz supplierBiz;

    /**
     * 供应商渠道
     */
    @RequestMapping("/Channel")
    @GetMapping("/")
    public String channel(Model model,Integer pageNo ,Integer cmId,Integer clId,Integer fId,
                          String com,String col,String pattern){
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
        Pager<Channel> pager=channelBiz.findChannel(pageNo,2,cmId,clId,fId);
        model.addAttribute("pager",pager);
        return "backstage/Channel";
    }

    /**
     * 添加供应商渠道页面
     */
    @RequestMapping(value = "/Channel_add",method = RequestMethod.GET)
    @GetMapping("/")
    public String channel_upd(Model model){
        List<Flower> flower=flowerBiz.findFlower();
        model.addAttribute("flower",flower);
        List<Component> components=flowerBiz.findComponent();
        model.addAttribute("components",components);
        List<Colour> colours=flowerBiz.findColour();
        model.addAttribute("colours",colours);
        List<Supplier> suppliers=supplierBiz.findSupplier();
        model.addAttribute("suppliers",suppliers);
        return "backstage/Channel_add";
    }
    /**
     * 添加供应商渠道
     */
    @RequestMapping(value = "/Channel_add",method = RequestMethod.POST)
    @GetMapping("/")
    public String channel_add(Model model,Channel channel){
        channelBiz.Add(channel);
        return "redirect:/Channel";
    }
    /**
     * 单个删除
     * @param id
     * @return
     */
    @RequestMapping("/delChannel")
    @GetMapping("/")
    public String del(Integer id){
        channelBiz.del(id);

        return "redirect:/Channel";
    }

    /**
     * 批量删除
     * @param arr
     * @return
     */
    @RequestMapping(value = "/delChannels",method = RequestMethod.POST)
    @ResponseBody
    @GetMapping("/")
    public String dels(@RequestParam(value = "arr[]") Integer[] arr){
        List<Integer> a= Arrays.asList(arr);
        for(Integer i:a){
            if(i!=null){
                //System.out.println(i);
                channelBiz.del(i);
            }

        }
        return "redirect:/Channel";
    }
}
