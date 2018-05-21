package com.accp.control;

import com.accp.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

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

	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(@RequestParam(required=false,defaultValue="xxx",value="uname") String username) {
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
