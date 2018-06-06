package com.accp.control;

import com.accp.biz.CooperationBiz;
import com.accp.entity.Cooperation;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializable;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.logging.log4j.util.Activator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CooperationController {

    @Resource
    private CooperationBiz cooperationBiz;

    @RequestMapping("/addCooperation")
    @ResponseBody
    private Map addCooperation(@RequestParam String QiYeName,String NAME,String CreateDate,String Phone,String QiYeAddress,
                                String zhiWu,String FaRen, String Email,String YeWuFanWei,String CanPingLei,
                                String CanNeng, String JiaGongXS,String CanPingFengGe,String PingPaiHeZuo,
                               String ZhongRenShu,String CheJIanRenShu,String BanFangRen,String ZaiChuangRenShu,
                               String DaTangRenShu,String HouDaoRenShu,String ChangMianJi,String ChenShu,
                               String ZaiChuangMianJi,String CheJianMianJi, String HouZhenMianJi,String ShuSheMianJi,
                                String YaoQiu){
        Map bool=new HashMap();

        try{
            Date createDate = new SimpleDateFormat("yyyy-MM-dd").parse(CreateDate);
            Cooperation cooperation=new Cooperation();
            cooperation.setYaoQiu(YaoQiu);
            cooperation.setBanFangRen(Integer.parseInt(BanFangRen));
            cooperation.setCanNeng(CanNeng);
            cooperation.setCanPingFengGe(CanPingFengGe);
            cooperation.setCanPingLei(CanPingLei);
            cooperation.setChangMianJi(Integer.parseInt(ChangMianJi));
            cooperation.setBanFangRen(Integer.parseInt(BanFangRen));
            cooperation.setCheJianMianJi(Integer.parseInt(CheJianMianJi));
            cooperation.setCheJIanRenShu(Integer.parseInt(CheJIanRenShu));
            cooperation.setChenShu(Integer.parseInt(ChenShu));
            cooperation.setCreateDate(createDate);
            cooperation.setDaTangRenShu(Integer.parseInt(DaTangRenShu));
            cooperation.setEmail(Email);
            cooperation.setFaRen(FaRen);
            cooperation.setHouDaoRenShu(Integer.parseInt(HouDaoRenShu));
            cooperation.setHouZhenMianJi(Integer.parseInt(HouZhenMianJi));
            cooperation.setJiaGongXS(JiaGongXS);
            cooperation.setNAME(NAME);
            cooperation.setPhone(Phone);
            cooperation.setPingPaiHeZuo(PingPaiHeZuo);
            cooperation.setQiYeAddress(QiYeAddress);
            cooperation.setQiYeName(QiYeName);
            cooperation.setShuSheMianJi(Integer.parseInt(ShuSheMianJi));
            cooperation.setYeWuFanWei(YeWuFanWei);
            cooperation.setZaiChuangMianJi(Integer.parseInt(ZaiChuangMianJi));
            cooperation.setZaiChuangRenShu(Integer.parseInt(ZaiChuangRenShu));
            cooperation.setZhiWu(zhiWu);
            cooperation.setZhongRenShu(Integer.parseInt(ZhongRenShu));
            if(cooperationBiz.addCooperation(cooperation)){
                    bool.put("status",1);
             }
        }catch (Exception e){

        }

        return bool;
    }
}
