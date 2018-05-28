package com.accp.control;

import com.accp.biz.FigureBiz;
import com.accp.biz.NewsBiz;
import com.accp.entity.News;
import com.accp.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class otherController {

    @Resource
    private FigureBiz figureBiz;

    @Resource
    private NewsBiz biz;
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
    @RequestMapping("/Daka")
    public String goToDake(){
        return "Daka";
    }


    // 保养手册
    @RequestMapping("/maintenance")
    public String goToMaintenance(){
        return "maintenance";
    }


    // 售后服务
    @RequestMapping("/service")
    public String goToService(){
        return "service";
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
    public String media(Model model,String type,Integer pageNo){
        if(type==null){
            type="公司动态";
        }
       // Pager<News> pager=biz.findNew(type,pageNo,6);
        model.addAttribute("page",biz.findNew(type,pageNo,6));
      /*  for(News n:pager.getDatas()){
            System.out.println(n.getTitle());
        }*/
      model.addAttribute("typeList",biz.findType());
        return "media";
    }

    /**
     * 12个分享页面
     * @return
     */
    @RequestMapping("/succeed_detail.html-sid=1")
    public String succeed_detail1(){
        return "succeed_detail.html-sid=1";
    }
    @RequestMapping("/succeed_detail.html-sid=2")
    public String succeed_detail2(){
        return "succeed_detail.html-sid=2";
    }
    @RequestMapping("/succeed_detail.html-sid=3")
    public String succeed_detail3(){
        return "succeed_detail.html-sid=3";
    }
    @RequestMapping("/succeed_detail.html-sid=4")
    public String succeed_detail4(){
        return "succeed_detail.html-sid=4";
    }
    @RequestMapping("/succeed_detail.html-sid=5")
    public String succeed_detail5(){
        return "succeed_detail.html-sid=5";
    }
    @RequestMapping("/succeed_detail.html-sid=6")
    public String succeed_detail6(){
        return "succeed_detail.html-sid=6";
    }
    @RequestMapping("/succeed_detail2.html-fashion_people_id=1")
    public String succeed_detail7(){
        return "succeed_detail2.html-fashion_people_id=1";
    }
    @RequestMapping("/succeed_detail2.html-fashion_people_id=2")
    public String succeed_detail8(){
        return "succeed_detail2.html-fashion_people_id=2";
    }
    @RequestMapping("/succeed_detail2.html-fashion_people_id=3")
    public String succeed_detail9(){
        return "succeed_detail2.html-fashion_people_id=3";
    }
    @RequestMapping("/succeed_detail2.html-fashion_people_id=4")
    public String succeed_detail10(){
        return "succeed_detail2.html-fashion_people_id=4";
    }
    @RequestMapping("/succeed_detail2.html-fashion_people_id=5")
    public String succeed_detail11(){
        return "succeed_detail2.html-fashion_people_id=5";
    }
    @RequestMapping("/succeed_detail2.html-fashion_people_id=6")
    public String succeed_detail12(){
        return "succeed_detail2.html-fashion_people_id=6";
    }

}
