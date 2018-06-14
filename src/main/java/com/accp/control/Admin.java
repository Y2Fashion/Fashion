package com.accp.control;

import com.accp.biz.*;
import com.accp.entity.*;
import com.accp.util.Pager;
import org.apache.commons.io.FilenameUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    @Resource
    private LiNingBiz liNingBiz;

    @Resource
    private PictureBiz pictureBiz;

    //实验后台页面
    @RequestMapping("/login")
    public String loGin() {
        return "login";
    }

    /*进入后台主页面 /login_go  /head   /left   /main*/
    @RequestMapping("/login_go")
    public String loGin_go() {
        return "/backstage/indexs";
    }

    @RequestMapping("/head")
    public String loGin_head() {
        return "/backstage/head";
    }

    @RequestMapping("/left")
    public String loGin_left() {
        return "/backstage/left";
    }

    @RequestMapping("/main")
    public String loGin_main() {
        return "/backstage/main";
    }

    /**
     * 订单管理页面
     *
     * @return
     */
    @RequestMapping("/order")
    public String loGin_order(Model model) {
        model.addAttribute("typeId", "1");
        return "/backstage/order";
    }

    /**
     * 进入添加订单页面
     *
     * @return
     */
    @RequestMapping("/order_add")
    public String order_add() {
        return "backstage/order_add";
    }

    /**
     * 进入查询订单页面
     *
     * @return
     */
    @RequestMapping("/order_get")
    public String order_get() {
        System.out.println("aaa");
        return "backstage/order_get";
    }

    /**
     * 进入订单修改页面
     *
     * @return
     */
    @RequestMapping("/order_upd")
    public String order_upd() {
        return "backstage/order_upd";
    }

    /**
     * 进入产品管理页面
     *
     * @return
     */
    @RequestMapping("/commodity")
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
    public String commodity_add(Model model, HttpServletResponse response, @ModelAttribute("commodity") Commodity commodity, BindingResult result, @RequestParam("fileImg") MultipartFile fileImg) {
        if (result.hasErrors()) {
            return "redirect:/commodity_add";
        }
        if (!fileImg.isEmpty()) {
            String path = "E:\\idea-workspace\\Fashion\\src\\main\\resources\\static\\image\\";
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
    public String commodity_get(@RequestParam("ids") Integer ids, Model model) {
        Commodity comm = commodityBiz.findId(ids);
        Lining lining = liNingBiz.getLiNingById(comm.getlId());
        ThirdType thirdType = thirdTypeBiz.getThirdType(comm.getType());
        model.addAttribute("type", thirdType.getThirdType());
        model.addAttribute("lining", lining.getIngredient());
        model.addAttribute("comm", comm);
        return "backstage/commodity_get";
    }

    /**
     * 查看产品关联图片
     *
     * @return
     */
    @RequestMapping("/commodity_show")
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
    public String update() {
        return "redirect:/commodity";
    }

    @RequestMapping("/commodity_del")
    @ResponseBody
    public String delete(Integer comId) {
        Integer id = commodityBiz.commoditydel(comId);
        return null;
    }


    @RequestMapping("/commodity_dels")
    @ResponseBody
    public String commodityDel(@RequestParam(value = "arr[]") Integer[] arr) {
        List<Integer> a = Arrays.asList(arr);
        for (Integer i : a) {
            if (i != null) {
                commodityBiz.commoditydel(i);
            }
        }
        return "redirect:/commodity";
    }
}
