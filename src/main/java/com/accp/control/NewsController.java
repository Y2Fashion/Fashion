package com.accp.control;

import com.accp.biz.NewsBiz;
import com.accp.biz.impl.NewsBizImpl;
import com.accp.entity.News;
import com.accp.util.RedisUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class NewsController {

    @Resource
    private NewsBizImpl newsBiz;
    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("getNews")
    @ResponseBody
    private List<News> getNews(Model model, Integer pageNo, String newsType){
        int pageStart=(pageNo-1)*6;
        List<News> newsList=(List<News>)redisUtil.lRange(newsType,0,redisUtil.length(newsType)-1).get(0);
        List<News> news=new ArrayList<News>();
        for(int i=pageStart;i<pageStart+6;i++){
            news.add(newsList.get(i));
        }
        Integer pageCotont=(newsList.size()+6-1)/6;
        model.addAttribute("pageCotont",pageCotont);
        return news;
    }

    // 新闻动态
    @RequestMapping("/media")
    public String goToMedia(Model model){
       List<News> news=new ArrayList<News>();
        List<News> newList=new ArrayList<News>();
        if(redisUtil.exists("公司动态")){
            newList=(List<News>)redisUtil.lRange("公司动态",0,redisUtil.length("公司动态")-1).get(0);
        }else{
            newList=newsBiz.getNews("公司动态");
            redisUtil.lPush("公司动态",newList);
        }
        for(int i=0;i<6&&newList.get(i)!=null;i++){
            news.add(newList.get(i));
        }
        Integer pageCount=(newList.size()+6-1)/6;
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("news1",news);
        return "media";
    }

    @RequestMapping("getNewsByType")
    @ResponseBody
    public List<News> getNewsByType(Model model,String newsType){
        List<News> news=new ArrayList<News>();
        List<News> newList=new ArrayList<News>();
        if(redisUtil.exists(newsType)){
            newList=(List<News>) redisUtil.lRange(newsType,0,redisUtil.length(newsType)-1).get(0);
        }else {
            newList = newsBiz.getNews(newsType);
            redisUtil.lPush(newsType,newList);

        }
        Integer pageCotont=(newList.size()+6-1)/6;
        model.addAttribute("pageCotont",pageCotont);
        for(int i=0;i<6&&newList.get(i)!=null;i++){
            news.add(newList.get(i));
        }
        return news;
    }
}
