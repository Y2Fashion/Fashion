package com.accp.control;

import com.accp.biz.CommodityBiz;
import com.accp.biz.FirstTypeBiz;
import com.accp.biz.SecondTypeBiz;
import com.accp.biz.ThirdTypeBiz;
import com.accp.entity.FirstType;
import com.accp.entity.SecondType;
import com.accp.entity.ThirdType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/***
 * 该控制类用于实验，具体请自己创建
 */
@Controller
public class Admin {
    @Resource
    private FirstTypeBiz firstTypeBiz;

    @Resource
    private SecondTypeBiz secondTypeBiz;

    @Resource
    private ThirdTypeBiz thirdTypeBiz;

    @Resource
    private CommodityBiz commodityBiz;
    //实验后台页面
    @RequestMapping("/login")
    public String loGin(){
        return "login";
    }

    /*进入后台主页面 /login_go  /head   /left   /main*/
    @RequestMapping("/login_go")
    public String loGin_go(){
        return "/backstage/indexs";
    }

    @RequestMapping("/head")
    public String loGin_head(){
        return "/backstage/head";
    }

    @RequestMapping("/left")
    public String loGin_left(){
        return "/backstage/left";
    }

    @RequestMapping("/main")
    public String loGin_main(){
        return "/backstage/main";
    }

    /**
     * 订单管理页面
     * @return
     */
    @RequestMapping("/order")
    public String loGin_order(Model model){

        model.addAttribute("typeId","1");
        return "/backstage/order";
    }

    /**
     * 进入添加订单页面
     * @return
     */
    @RequestMapping("/order_add")
    public String order_add(){
        return "backstage/order_add";
    }
    /**
     * 进入查询订单页面
     * @return
     */
    @RequestMapping("/order_get")
    public String order_get(){
        System.out.println("aaa");
        return "backstage/order_get";
    }
    /**
     * 进入订单修改页面
     * @return
     */
    @RequestMapping("/order_upd")
    public String order_upd(){
        return "backstage/order_upd";
    }


    /**
     * 进入产品管理页面
     * @return
     */
    @RequestMapping("/commodity")
    public String commodity(Model model,Integer type,Integer pageNo){
        FirstType firstType = new FirstType();
        firstType.setfId(0);
        firstType.setFirstType("请选择");
        firstTypeBiz.getList().add(firstType);
        model.addAttribute("firstTypeList",firstTypeBiz.getList());
        model.addAttribute("secondTypeList",secondTypeBiz.getList());
        model.addAttribute("thirdTypeList",thirdTypeBiz.getThirdTypeList(null));
        model.addAttribute("page",commodityBiz.commodityList(type,pageNo));
        return "backstage/commodity";
    }

    /**
     * 进入添加产品页面
     * @return
     */
    @RequestMapping("/commodity_add")
    public String commodity_add(Model model){
        return "backstage/commodity_add";
    }

    /**
     * 进入查看产品页面
     * @return
     */
    @RequestMapping("/commodity_get")
    public String commodity_get(){
        return "backstage/commodity_get";
    }

    /**
     * 查看产品关联图片
     * @return
     */
    @RequestMapping("/commodity_show")
    public String commodity_show(){
        return "backstage/commodity_show";
    }

    /**
     * 进入修改产品页面
     * @return
     */
    @RequestMapping("/commodity_upd")
    public String commodity_upd(){
        return "backstage/commodity_upd";
    }

    /**
     * 进入修改产品关联图片
     * @return
     */
    @RequestMapping("/commodity_updImg")
    public String commodity_updImg(){
        return "backstage/commodity_updImg";
    }


    /**
     * 进入布料库存
     */
    @RequestMapping("/lining")
    public String lining(){
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
    @RequestMapping("/lining_get")
    public String lining_get(){
        return "backstage/lining_get";
    }

    /**
     * 进入布料库存--修改
     */
    @RequestMapping("/lining_upd")
    public String lining_upd(){
        return "backstage/lining_upd";
    }

    /**
     * 进入布料使用情况
     */
    @RequestMapping("/Use")
    public String use(){
        return "backstage/Use";
    }

    /**
     * 添加布料使用情况
     */
    @RequestMapping("/Use_add")
    public String use_add(){
        return "backstage/Use_add";
    }

    /**
     *布料进货
     */
    @RequestMapping("/Purchase")
    public String purchase(){
        return "backstage/Purchase";
    }

    /**
     * 添加布料进货
     */
    @RequestMapping("/Purchase_add")
    public String purchase_add(){
        return "backstage/Purchase_add";
    }

    /**
     * 查看布料进货订单
     */
    @RequestMapping("/Purchase_get")
    public String purchase_get(){
        return "backstage/Purchase_get";
    }

    /**
     * 修改布料进货订单
     */
    @RequestMapping("/Purchase_upd")
    public String purchase_upd(){
        return "backstage/Purchase_upd";
    }

    /**
     * 供应商渠道
     */
    @RequestMapping("/Channel")
    public String channel(){
        return "backstage/Channel";
    }

    /**
     * 添加供应商渠道
     */
    @RequestMapping("/Channel_add")
    public String channel_upd(){
        return "backstage/Channel_add";
    }

    /**
     *供应商
     */
    @RequestMapping("/Supplier")
    public String supplier(){
        return "backstage/Supplier";
    }

    /**
     * 添加供应商
     */
    @RequestMapping("/Supplier_add")
    public String supplier_add(){
        return "backstage/Supplier_add";
    }

    /**
     * 查看供应商
     */
    @RequestMapping("/Supplier_get")
    public String supplier_get(){
        return "backstage/Supplier_get";
    }

    /**
     * 修改供应商
     */
    @RequestMapping("/Supplier_upd")
    public String supplier_upd(){
        return "backstage/Supplier_upd";
    }
}
