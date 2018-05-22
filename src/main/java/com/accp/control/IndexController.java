package com.accp.control;

import com.accp.biz.FirstTypeBiz;
import com.accp.biz.SecondTypeBiz;
import com.accp.dao.RedisDao;
import com.accp.entity.FirstType;
import com.accp.entity.SecondType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * 实验SSM框架整合是否有问题
 * 2018-5-7
 */
@Controller
public class IndexController {

	/* redis */
	@Autowired
	RedisDao redisDao;
    @Resource
	private  FirstTypeBiz firstBiz;

    @Resource
    private SecondTypeBiz secondBiz;

	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(Model model) {
		List<FirstType> first=firstBiz.getList();//得到一级表的全部信息
		List<SecondType> second=secondBiz.getList();//得到二级表的全部信息
		List<SecondType> sss=new ArrayList<SecondType>();
		for (SecondType li:second) {//index页面的产品
			for (FirstType li2:first) {
				if(li.getfId()==li2.getfId()){
					String type=li2.getFirstType()+li.getSecondType();
					li.setSecondType(type);
					sss.add(li);
					break;
				}
			}
		}
		model.addAttribute("secondType", sss);
		//修改了
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping(value = "/bigEvent",method = RequestMethod.GET)
	public ModelAndView bigEvent(){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bigEvent");
		return mav;
	}

	@RequestMapping(value = "/make",method = RequestMethod.GET)
	public ModelAndView make(){
		ModelAndView view = new ModelAndView();
		view.setViewName("pc-baoyang");
		return view;
	}


}
