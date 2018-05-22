package com.accp.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class otherController {
   //大事记网页
    @RequestMapping("/bigEvent")
    public String goToBigEvent(){
        return "bigEvent";
    }

    //品牌合作
    @RequestMapping("/brandCooper")
    public String goToBrandCooper(){
        return "brandCooper";
    }

    // 商务合作
    @RequestMapping("/business")
    public String goToBusiness(){
        return "business";
    }

    // 关于我们
    @RequestMapping("/company")
    public String goToCompany(){
        return "company";
    }

    // 联系我们
    @RequestMapping("/contact")
    public String goToContact(){
        return "contact";
    }

    // 大咖定制秀
    @RequestMapping("/Dake")
    public String goToDake(){
        return "Dake";
    }


    // 保养手册
    @RequestMapping("/maintenance")
    public String goToMaintenance(){
        return "maintenance";
    }


    // 售后服务
    @RequestMapping("/service")
    public String goToService(){
        return "servicet";
    }

    // 风尚人物
    @RequestMapping("/succeed")
    public String goToSucceed(){
        return "succeed";
    }

    // 用户协议
    @RequestMapping("/userAgreement")
    public String goToUserAgreement(){
        return "userAgreement";
    }

    /**
     * 预约量体
     * @return
     */
    @RequestMapping("/yuyue")
    public String yuyue(){
        return "yuyue";
    }
    /**
     * 新闻动态
     * @return
     */
    @RequestMapping("/media")
    public String media(){
        return "media";
    }

}
