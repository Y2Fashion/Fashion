package com.accp.control;

import com.accp.biz.FirstTypeBiz;
import com.accp.biz.SecondTypeBiz;
import com.accp.entity.FirstType;
import com.accp.entity.SecondType;
import com.accp.util.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {


    @Resource
	private  FirstTypeBiz firstBiz;

    @Resource
	private RedisUtil redisUtil;

    @Resource
    private SecondTypeBiz secondBiz;

    //下拉查询
	@RequestMapping("/pull")
	@ResponseBody
	@GetMapping("/")
	public List<SecondType> pull() {
		List<FirstType> first=null;
		List<SecondType> second=null;
		List<SecondType> sss = new ArrayList<SecondType>();
		if(redisUtil.exists("pull")){
			sss=(List<SecondType>)redisUtil.lRange("pull",0,redisUtil.length("pull")).get(0);
		}else {
			first = firstBiz.getList();//得到一级表的全部信息
			second = secondBiz.getList();//得到二级表的全部信息
			sss = new ArrayList<SecondType>();
			for (SecondType li : second) {//index页面的产品
				for (FirstType li2 : first) {
					if (li.getfId() == li2.getfId()) {
						String type = li2.getFirstType() + li.getSecondType();
						li.setSecondType(type);
						sss.add(li);
						break;
					}
				}
			}
			redisUtil.lPush("pull",sss);
		}
		return sss;
	}

	@RequestMapping(value = "/make",method = RequestMethod.GET)
	@GetMapping("/")
	public ModelAndView make(){
		ModelAndView view = new ModelAndView();
		view.setViewName("pc-baoyang");
		return view;
	}


}
