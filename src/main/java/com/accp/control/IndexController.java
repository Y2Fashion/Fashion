package com.accp.control;

import com.accp.biz.Smbms_UserService;
import com.accp.biz.Smbms_roleService;
import com.accp.dao.RedisDao;
import com.accp.entity.Smbms_role;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/***
 *
 * 实验SSM框架整合是否有问题
 * 2018-5-7
 */
@Controller
public class IndexController {
	@Resource
	private Smbms_UserService userService;

	@Resource
	private Smbms_roleService roleService;

	/* redis */
	@Autowired
	RedisDao redisDao;

	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(@RequestParam(required=false,defaultValue="xxx",value="uname") String username) {
		/*String str = "学框架就学Spring  MVC";*/
		ModelAndView mav = new ModelAndView();
		testRedis();
		/*mav.addObject("message", str);*/
		mav.setViewName("indexs");
		return mav;
	}

	/*
	 * 验证用户名与密码是否一致
	 * @param userCode  用户名
	 * @param userPassword 密码
	 * @return 返回页面
	 */
	@RequestMapping("/add")
	public String add(String userCode, String userPassword,Model model) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@"+redisDao.getValue("rrrrr"));
		System.out.println("userCode");
		System.out.println("userPassword");
		if(userService.list(userCode,userPassword)>0){
			return "add";
		}
		return "indexs";
	}

	/*redis*/
	public void testRedis(){
        redisDao.remove("roleList");
		List<Smbms_role> rr=roleService.list();
		String str=null;
		try{
			str= JSON.toJSONString(rr);//转换成JSON格式
		}catch (Exception e){
			System.out.println("出现转换格式错误！");
		}
        try{
		    boolean fff=redisDao.exists("roleList");
            redisDao.addList("roleList",rr);
        }catch (Exception e){
            System.out.println("保存错误！");
        }
        boolean xxx=redisDao.exists("name");
        System.out.println("%%%%%%%%%%%%%%%%%%"+xxx);
        redisDao.setKey("name","forezp");
		redisDao.setKey("age","11");
		boolean zzz=redisDao.exists("name");
        System.out.println("%%%%%%%%%%%%%%%%%%"+zzz);
		redisDao.setKey("rrrrr",str);

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@"+redisDao.getValue("name"));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@"+redisDao.getValue("age"));
		System.out.println("#######################"+redisDao.getValue("rrrrr")+"###########################");
        List<Object> list2s=redisDao.getList("roleList");
        boolean sss=redisDao.exists("roleList");
        for (Object item:list2s) {
            List<Smbms_role> list3s=(List<Smbms_role>)item;
            for (Smbms_role rs2:list3s) {
                System.out.println("输出List中的数据："+rs2.getRoleName()+"--------"+rs2.getCreatedBy());
            }
        }
	}

}
