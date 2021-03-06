package com.accp.control;

import com.accp.biz.*;
import com.accp.entity.*;
import com.accp.util.Pager;
import org.apache.commons.io.FilenameUtils;

import com.accp.biz.CommodityBiz;
import com.accp.biz.FirstTypeBiz;
import com.accp.biz.SecondTypeBiz;
import com.accp.biz.ThirdTypeBiz;
import com.accp.entity.FirstType;

import com.accp.entity.ThirdType;
import com.accp.biz.OrderBiz;
import com.accp.biz.StatusBiz;
import com.accp.entity.Order;



import com.accp.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/***
 * 该控制类用于实验，具体请自己创建
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;


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

    @Resource
    private LiNingBiz liNingBiz;

    @Resource
    private PictureBiz pictureBiz;

    @Resource
    private UserOrderBiz userOrderBiz;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private StatusBiz statusBiz;


    @Resource
    private OrderBiz orderBiz;








    /**
     * 进入产品管理页面
     *
     * @return
     */
    @RequestMapping("/commodity")
    @GetMapping("/")
    public String commodity(Model model, Integer pageNo) {
        FirstType fType = new FirstType();
        fType.setfId(0);
        fType.setFirstType("请选择");
        List<FirstType> firstTypeList = firstTypeBiz.getList();
        firstTypeList.add(0, fType);
        model.addAttribute("firstTypeList", firstTypeList);
        model.addAttribute("pageNo", commodityBiz.commodityList(null, null, null, pageNo).getPageNo());
        model.addAttribute("totalPage", commodityBiz.commodityList(null, null, null, pageNo).getTotalPage());
        model.addAttribute("totalRows", commodityBiz.commodityList(null, null, null, pageNo).getTotalRows());
        model.addAttribute("commodity", commodityBiz.commodityList(null, null, null, pageNo).getDatas());
        return "backstage/commodity";
    }

    @RequestMapping("/commodityAjax")
    @ResponseBody
    @GetMapping("/")
    public Pager<Commodity> commodityAjax(Model model, @RequestParam(value = "type[]") Integer[] type, Integer pageNo) {

        if (type.length == 1) {
            model.addAttribute("pageNo", commodityBiz.commodityList(null, null, null, pageNo).getPageNo());
            model.addAttribute("totalPage", commodityBiz.commodityList(null, null, null, pageNo).getTotalPage());
            return commodityBiz.commodityList(null, null, null, pageNo);
        }
        if (type.length < 3) {
            model.addAttribute("pageNo", commodityBiz.commodityList(null, type[1], type[0], pageNo).getPageNo());
            model.addAttribute("totalPage", commodityBiz.commodityList(null, type[1], type[0], pageNo).getTotalPage());
            return commodityBiz.commodityList(null, type[1], type[0], pageNo);
        } else {
            model.addAttribute("pageNo", commodityBiz.commodityList(type[2], type[1], type[0], pageNo).getPageNo());
            model.addAttribute("totalPage", commodityBiz.commodityList(type[2], type[1], type[0], pageNo).getTotalPage());
            return commodityBiz.commodityList(type[2], type[1], type[0], pageNo);
        }
    }

    @RequestMapping("/getCommodityByType")
    @ResponseBody
    @GetMapping("/")
    public Pager<Commodity> getCommodityByType(Integer type[], Integer pageNo) {
       /* Integer thirdType=null;
        Integer secondType=null;
        Integer firstType=null;
        for (int i=0;i<type.length;i++){

        }*/
        return commodityBiz.commodityList(type[0], type[1], type[2], pageNo);
    }

    /**
     * 进入添加产品页面
     *
     * @return
     */
    @RequestMapping(value = "/commodity_add", method = RequestMethod.GET)
    @GetMapping("/")
    public String commodity_add(Model model) {
        FirstType firstType = new FirstType();
        firstType.setfId(0);
        firstType.setFirstType("请选择");
        List<FirstType> firstTypeList = firstTypeBiz.getList();
        firstTypeList.add(0, firstType);
        model.addAttribute("firstTypeList", firstTypeList);
        model.addAttribute("lining", liNingBiz.getLiNingList());
        return "backstage/commodity_add";
    }

    @RequestMapping(value = "/commodity_add", method = RequestMethod.POST)
    @GetMapping("/")
    public String commodity_add(Model model, HttpServletResponse response, @ModelAttribute("commodity") Commodity commodity, BindingResult result, @RequestParam("fileImg") MultipartFile fileImg) {
        if (result.hasErrors()) {
            return "redirect:/commodity_add";
        }
        if (!fileImg.isEmpty()) {
            String path = "E:\\05李石祥\\Y2\\Fashion\\src\\main\\resources\\static\\image\\";
            String uploadFilename = fileImg.getOriginalFilename();
            String ext = FilenameUtils.getExtension(uploadFilename);
            if (fileImg.getSize() > 5000000) {
                model.addAttribute("uploadError", "文件大小超过限制(5MB)");
                return "redirect:/commodity_add";
            }
            if ("jpg".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext) || "png".equalsIgnoreCase(ext)
                    || "pneg".equalsIgnoreCase(ext) || "gif".equalsIgnoreCase(ext)) {
                String filename = "pic" + System.currentTimeMillis() + (new Random().nextInt(10000)) + "." + ext;
                File file = new File(path + filename);
                BufferedOutputStream out = null;
                try {
                    out = new BufferedOutputStream(new FileOutputStream(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if (!file.exists()) {
                    file.mkdirs();
                }
                try {
                    out.write(fileImg.getBytes());
                    commodity.setPhotoPath(filename);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("uploadError", "文件上传出错");
                    return "redirect:/commodity_add";
                }

            } else {
                model.addAttribute("uploadError", "文件类型不符合要求(jpg,png)");
                return "redirect:/commodity_add";
            }
        }

        if (commodityBiz.insertCommodity(commodity)) {
            return "redirect:/commodity";
        } else {
            return "redirect:/commodity_add";
        }
    }

    /**
     * 进入查看产品页面
     *
     * @return
     */
    @RequestMapping("/commodity_get")
    @GetMapping("/")
    public String commodity_get(@RequestParam("ids") Integer ids, Model model) {
        Commodity comm = commodityBiz.findId(ids);
        Lining lining = liNingBiz.getLiNingById(comm.getlId());
        ThirdType thirdType = thirdTypeBiz.getThirdType(comm.getType());
        model.addAttribute("type", thirdType.getThirdType());
        if(lining !=null){
            model.addAttribute("lining", lining.getIngredient());
        }
        model.addAttribute("comm", comm);
        return "backstage/commodity_get";
    }

    /**
     * 查看产品关联图片
     *
     * @return
     */
    @RequestMapping("/commodity_show")
    @GetMapping("/")
    public String commodity_show(@RequestParam("comid") Integer comid, Model model) {
        model.addAttribute("picture", pictureBiz.getPictureList(comid));
        return "backstage/commodity_show";
    }

    /**
     * 进入修改产品页面
     *
     * @return
     */
    @RequestMapping(value = "/commodity_upd", method = RequestMethod.GET)
    @GetMapping("/")
    public String commodity_upd(Model model, @RequestParam("ids") Integer ids) {
        Commodity comm = commodityBiz.findId(ids);
        Lining lining = liNingBiz.getLiNingById(comm.getlId());
        ThirdType thirdType = thirdTypeBiz.getThirdType(comm.getType());
        model.addAttribute("type", thirdType.getThirdType());
        model.addAttribute("lining", lining.getIngredient());
        model.addAttribute("comm", comm);
        return "backstage/commodity_upd";
    }

    @RequestMapping(value = "/commodity_upd", method = RequestMethod.POST)
    @ResponseBody
    @GetMapping("/")
    public String commodity_upd(HttpServletResponse response,Commodity commodity) throws Exception{

        String flag=" ";
       if(commodityBiz.updataCommodity(commodity)){
           response.sendRedirect("/commodity");
           return flag;
       }else{
           flag="false";
           return flag;
       }

    }

    @RequestMapping("/getSecondType")
    @ResponseBody
    @GetMapping("/")
    public List<SecondType> getSecondType(Model model, Integer firstType) {
        SecondType secondType = new SecondType();
        secondType.setSecondType("请选择");
        secondType.setsId(0);
        List<SecondType> secondTypeList = secondTypeBiz.getListById(firstType);
        secondTypeList.add(0, secondType);
        return secondTypeList;
    }

    @RequestMapping("/getThirdType")
    @ResponseBody
    @GetMapping("/")
    public List<ThirdType> getThirdType(Integer secondType) {
        ThirdType thirdType = new ThirdType();
        thirdType.setsId(null);
        thirdType.settId(0);
        thirdType.setThirdType("请选择");
        List<ThirdType> thirdTypeList = thirdTypeBiz.getThirdTypeList(secondType);
        thirdTypeList.add(0, thirdType);

        return thirdTypeList;
    }

    @RequestMapping("/aaa")
    @GetMapping("/")
    public String update() {
        return "redirect:/commodity";
    }

    @RequestMapping("/commodity_del")
    @ResponseBody
    @GetMapping("/")
    public String delete(Integer comId) {
        Integer id = commodityBiz.commoditydel(comId);
        return null;
    }


    @RequestMapping("/commodity_dels")
    @ResponseBody
    @GetMapping("/")
    public String commodityDel(@RequestParam(value = "arr[]") Integer[] arr) {
        List<Integer> a = Arrays.asList(arr);
        for (Integer i : a) {
            if (i != null) {
                commodityBiz.commoditydel(i);
            }
        }
        return "redirect:/commodity";
    }

    /**
     * 进入修改产品关联图片
     * @return
     */
    @RequestMapping("/commodity_updImg")
    @GetMapping("/")
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
    @GetMapping("/")
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


    /*
     * 柱形算图
     * */
    @RequestMapping("/CommodityTopView")
    @GetMapping("/")
    public String goZView(Model model){

        List<String>dataName=new ArrayList<String>();
        List<Integer> datas=new ArrayList<Integer>();
        JSON dataNameJ=null;
        JSON datasJ=null;
        redisUtil.remove("dataNameJ");
        if(redisUtil.exists("dataNameJ")){
            dataNameJ=(JSON)redisUtil.lRange("dataNameJ",0,redisUtil.length("dataNameJ")).get(0);
            datasJ=(JSON)redisUtil.lRange("datasJ",0,redisUtil.length("datasJ")).get(0);
        }else{
            List<UserOrder> UserOrders=userOrderBiz.getCommdityTop10();
            if(UserOrders.size()>0){
                for (int i=UserOrders.size()-1;i>=0;i--) {
                    UserOrders.get(i).setCommodity(commodityBiz.getCommodityById(UserOrders.get(i).getCommodityId()));
                    dataName.add(UserOrders.get(i).getCommodity().getCommodityName());
                    datas.add(UserOrders.get(i).getCountCID());
                }
                dataNameJ=(JSON)JSON.toJSON(dataName);
                datasJ=(JSON)JSON.toJSON(datas);
            }
            redisUtil.lPush("dataNameJ",dataNameJ);
            redisUtil.lPush("datasJ",datasJ);
        }
        model.addAttribute("dataName",dataNameJ);
        model.addAttribute("datas",datasJ);
        return "backstage/CommodityTopView";
    }

    @RequestMapping("/ThreeTypeView")
    @GetMapping("/")
    public String goThreeTypeView(Model model){

        List<String> typeName=new ArrayList<String>();
        List<view> views=new ArrayList<view>();
        JSON view=null;
        JSON typeNames=null;
        if(redisUtil.exists("view")){
            view=(JSON)redisUtil.lRange("view",0,redisUtil.length("view")).get(0);
            typeNames=(JSON)redisUtil.lRange("typeNames",0,redisUtil.length("typeNames")).get(0);
        }else{
            List<Commodity> commodities=commodityBiz.getHitsGroupType();
            List<ThirdType> thirdTypes=thirdTypeBiz.getThirdTypeByCArray(commodities);
            for (ThirdType t:thirdTypes) {
                if(t.getsId()<7){
                    t.setThirdType("男式"+t.getThirdType());
                }else if(t.getsId()>=8){
                    t.setThirdType("女式"+t.getThirdType());
                }
                typeName.add(t.getThirdType());
            }
            for(int i=0;i<commodities.size();i++){
                views.add(new view(thirdTypes.get(i).getThirdType(),commodities.get(i).getHits()));
            }
            view=(JSON)JSON.toJSON(views);
            typeNames=(JSON)JSON.toJSON(typeName);
        }

        model.addAttribute("view",view);
        model.addAttribute("typeName",typeNames);
        return "backstage/ThreeTypeView";
    }

    @RequestMapping("/lineView")
    @GetMapping("/")
    private String goToLineView(Model model){
        List<UserOrder> userOrders=new ArrayList<UserOrder>();
        List<String> dataName=new ArrayList<String>();
        List<ThirdType> thirdTypes=new ArrayList<ThirdType>();
        Integer[][] userOrderLists=null;
        JSON dataNameJ=null;
        JSON datasJ=null;
        if(redisUtil.exists("lineDataName")){
            dataNameJ=(JSON)redisUtil.lRange("lineDataName",0,redisUtil.length("lineDataName")).get(0);
            datasJ=(JSON)redisUtil.lRange("lineDatas",0,redisUtil.length("lineDatas")).get(0);
        }else{
            userOrders=userOrderBiz.getThreeTypes();
            thirdTypes=thirdTypeBiz.getThirdName(userOrders);
            userOrderLists=new Integer[userOrders.size()][];
            for (ThirdType t : thirdTypes) {
                if(t.getsId()>=8){
                    dataName.add("女式"+t.getThirdType());
                }else if(t.getsId()<7){
                    dataName.add("男式"+t.getThirdType());
                }
            }
            int b=0;
            for (UserOrder userOrder:userOrders) {
                List<UserOrder> userOrders1=userOrderBiz.getSaleByMonth(userOrder.getThreeTypeId());
                Integer[] ints=new Integer[12];
                for (UserOrder u:userOrders1) {
                    for(int j=1;j<=12;j++){
                        if(u.getTime()==j){
                            ints[j-1]=u.getCountCID();
                        }else{
                            if(ints[j-1]==null){
                                ints[j-1]=0;
                            }
                        }
                    }
                }
                userOrderLists[b]=ints;
                b++;
            }
            dataNameJ=(JSON)JSON.toJSON(dataName);
            datasJ=(JSON)JSON.toJSON(userOrderLists);
            redisUtil.lPush("lineDataName",dataNameJ);
            redisUtil.lPush("lineDatas",datasJ);
        }

        model.addAttribute("dataName",dataNameJ);
        model.addAttribute("datas",datasJ);
        return "backstage/lineView";
    }

    @RequestMapping("BazaarView")
    @GetMapping("/")
    public String goVazaarView(Model model){
        List<UserOrder> userOrders=new ArrayList<UserOrder>();
        List<String> dataName=new ArrayList<String>();
        Integer[] datas=null;
        JSON dataNameJ=null;
        JSON datasJ=null;
        redisUtil.remove("BazaarDataName");
        redisUtil.remove("BazaarDatas");
        redisUtil.remove("BazaarDataName");
        if(redisUtil.exists("BazaarDataName")){
            dataNameJ=(JSON)redisUtil.lRange("BazaarDataName",0,redisUtil.length("BazaarDataName")).get(0);
            datasJ=(JSON)redisUtil.lRange("BazaarDatas",0,redisUtil.length("BazaarDatas")).get(0);
        }else{
            userOrders=userOrderBiz.getSaleByAddress();
            datas=new Integer[userOrders.size()];
            int i=0;
            for (UserOrder u:userOrders) {
                datas[i]=u.getCountCID();
                String name=u.getAddress();
                if(name.indexOf("上海")!=-1||name.indexOf("北京")!=-1||name.indexOf("天津")!=-1||name.indexOf("香港")!=-1||name.indexOf("澳门")!=-1||name.indexOf("广西")!=-1||name.indexOf("西藏")!=-1||name.indexOf("宁夏")!=-1||name.indexOf("新疆")!=-1){
                    name=name.substring(0,2);
                }
                if(name.indexOf("内蒙古")!=-1||name.indexOf("黑龙江")!=-1){
                    name=name.substring(0,3);
                }
                dataName.add(name);
                i++;
            }
            dataNameJ=(JSON)JSON.toJSON(dataName);
            datasJ=(JSON)JSON.toJSON(datas);
            redisUtil.lPush("BazaarDataName",dataNameJ);
            redisUtil.lPush("BazaarDatas",datasJ);
        }
        model.addAttribute("dataName",dataNameJ);
        model.addAttribute("datas",datasJ);
        return "backstage/BazaarView";
    }

}
