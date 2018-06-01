package com.accp.control;

import com.accp.biz.FigureBiz;
import com.accp.biz.NewsBiz;
import com.accp.biz.OrderBiz;
import com.accp.entity.News;
import com.accp.entity.Order;
import com.accp.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class otherController {

    @Resource
    private FigureBiz figureBiz;
    @Resource
    private OrderBiz orderBiz;
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
    public String goToSucceed(Model model){
        model.addAttribute("figureList",figureBiz.getFigureList());
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
    public String yuyue(Model model,String status){
        int num=orderBiz.findCount(status);
        model.addAttribute("num",num);
        return "yuyue";
    }
    @RequestMapping("/AddOrder")
    @ResponseBody
    public String AddOrder(String name,String phone,String address,String com){
        Order order=new Order();
        order.setClienteleAddress(address);
        order.setClienteleName(name);
        order.setClientelePhone(phone);
        order.setComment(com);
        orderBiz.Add(order);
        return null;
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

    @RequestMapping("/succeed_detail21")
    public String succeed_detail21(){
        return "succeed_detail.html-sid=1";
    }

    @RequestMapping("/succeed_detail22")
    public String succeed_detail22(){
        return "succeed_detail.html-sid=2";
    }

    @RequestMapping("/succeed_detail23")
    public String succeed_detail23(){
        return "succeed_detail.html-sid=3";
    }

    @RequestMapping("/succeed_detail24")
    public String succeed_detail24(){
        return "succeed_detail.html-sid=4";
    }

    @RequestMapping("/succeed_detail25")
    public String succeed_detail25(){
        return "succeed_detail.html-sid=5";
    }

    @RequestMapping("/succeed_detail26")
    public String succeed_detail26(){
        return "succeed_detail.html-sid=6";
    }

    @RequestMapping("/succeed_detail1")
    public String succeed_detail1(){
        return "succeed_detail2.html-fashion_people_id=1";
    }

    @RequestMapping("/succeed_detail2")
    public String succeed_detail2(){
        return "succeed_detail2.html-fashion_people_id=2";
    }

    @RequestMapping("/succeed_detai3")
    public String succeed_detail3(){
        return "succeed_detail2.html-fashion_people_id=3";
    }

    @RequestMapping("/succeed_detail4")
    public String succeed_detail4(){
        return "succeed_detail2.html-fashion_people_id=4";
    }

    @RequestMapping("/succeed_detail5")
    public String succeed_detail5(){
        return "succeed_detail2.html-fashion_people_id=5";
    }

    @RequestMapping("/succeed_detail6")
    public String succeed_detail6(){
        return "succeed_detail2.html-fashion_people_id=6";
    }

    @RequestMapping("/media_detail171")
    public String media_detail171(){
        return "media_detail.html-article_id=171";
    }

    @RequestMapping("/media_detail172")
    public String media_detail172(){
        return "media_detail.html-article_id=172";
    }

    @RequestMapping("/media_detail173")
    public String media_detail173(){
        return "media_detail.html-article_id=173";
    }

    @RequestMapping("/media_detail174")
    public String media_detail174(){
        return "media_detail.html-article_id=174";
    }

    @RequestMapping("/media_detail175")
    public String media_detail175(){
        return "media_detail.html-article_id=175";
    }

    @RequestMapping("/media_detail176")
    public String media_detail176(){
        return "media_detail.html-article_id=176";
    }

    @RequestMapping("/media_detail177")
    public String media_detail177(){
        return "media_detail.html-article_id=177";
    }

    @RequestMapping("/media_detail178")
    public String media_detail178(){
        return "media_detail.html-article_id=178";
    }

    @RequestMapping("/SupplierReg")
    public String SupplierReg(){
        return "SupplierReg";
    }
}
