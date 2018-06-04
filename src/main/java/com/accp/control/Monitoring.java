package com.accp.control;

import com.accp.biz.AccessingDataBiz;
import com.accp.entity.AccessingData;
import com.accp.util.Iputil;
import com.accp.util.RedisUtil;
import com.accp.util.Storage;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;

@Controller
@Configuration
@EnableScheduling
public class Monitoring {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private AccessingDataBiz accessingDataBiz;

    /*
    * 定时器：每1分钟向数据库保存与更新数据库
    * */
    @Scheduled(cron = "0 0/1 * * * ?")
    private void ModifyAccessingData(){
        System.out.println("成功！");
        if(redisUtil.exists("userIP")){
            String IP= (String) redisUtil.lRange("userIP",0,redisUtil.length("userIP")).get(0);
            if(Storage.accessingData.size()>0){
                //List<AccessingData> accessingDatas=(List<AccessingData>) redisUtil.lRange(IP,0,redisUtil.length(IP)).get(0);
                List<AccessingData> accessingDatas=Storage.accessingData;
                for (AccessingData a:accessingDatas) {
                    if(a.getTypeID()==null||a.getTypeID()==0){
                        a.setTypeID(null);
                        if(accessingDataBiz.getAccessingDataByC(a.getIp(),a.getcId())){
                            accessingDataBiz.modifyAccessingData(a);
                        }else {
                            accessingDataBiz.addAccessingData(a);
                        }
                    }
                    if(a.getcId()==null||a.getcId()==0){
                        a.setcId(null);
                        if(accessingDataBiz.getAccessingDataByT(a.getIp(),a.getTypeID())){
                            accessingDataBiz.modifyAccessingData(a);
                        }else {
                            accessingDataBiz.addAccessingData(a);
                        }
                    }

                }
            }
        }

    }
}
