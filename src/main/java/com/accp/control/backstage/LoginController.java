package com.accp.control.backstage;

import com.accp.biz.UserBiz;
import com.accp.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登录后台页面操作
 */
@Controller
public class LoginController {
    @Resource
    private UserBiz userBiz;

    //进入后台登录页面
    @RequestMapping("/login")
    public String loGin(Model model){
        return "login";
    }
//    https://blog.csdn.net/hcwbr123/article/details/70885388
//    https://blog.csdn.net/u014266877/article/details/54093596
//    http://www.zixuephp.net/article-16.html    md5
//    https://blog.csdn.net/sunshine_han/article/details/78951834



    /*进入后台主页面 /login_go  /head   /left   /main*/
    @RequestMapping("/login_go")
    public String loGin_go(Model model, String username, String pwd , HttpSession session){
        User user=new User(username,pwd,"已启用");
        User users=userBiz.get(user);
        if(users!=null&&users.getId()>0){
            users.setUserPwd("");
//            String tishi=users.getUserRole()+":"+users.getUserName();
            session.setAttribute("user_role",users);
            return "/backstage/indexs";
        }else{
            model.addAttribute("error_login","用户名与密码不一致");
            return "login";
        }
    }

    @RequestMapping("/head")
    public String loGin_head(Model model,HttpSession session){
        User role=(User)session.getAttribute("user_role");
        model.addAttribute("user_role",role);
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
}
