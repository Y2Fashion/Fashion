package com.accp.control.backstage;

import com.accp.biz.UserBiz;
import com.accp.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/")
    public String loGin_go(Model model, String username, String pwd , HttpSession session){
        if(username==null){
            username="-1111111";
        }
        if(pwd ==null){
            pwd="-11111111";
        }
        User user=new User(username,pwd,"已启用");
        User users=userBiz.get(user);
        if(users!=null&&users.getId()>0){
            session.setAttribute("user_role",users);
            return "forward:/home";
        }else{
            model.addAttribute("error_login","用户名与密码不一致");
            return "login";
        }
    }
    @RequestMapping("/home")
    @GetMapping("/")
    public String index(HttpSession session){
        if(session.getAttribute("user_role")!=null){
            return "/backstage/indexs";
        }
            return "/login";

    }

    @RequestMapping("/head")
    @GetMapping("/")
    public String loGin_head(Model model,HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        User role=(User)session.getAttribute("user_role");
        model.addAttribute("user_role",role);
        return "/backstage/head";
    }

    @RequestMapping("/left")
    public String loGin_left(Model model,HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        User role=(User)session.getAttribute("user_role");
        model.addAttribute("user_role",role);
        return "/backstage/left";
    }

    @RequestMapping("/main")
    public String loGin_main(HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        return "/backstage/main";
    }

    /**
     * 退出系统,关闭会话,返回登录页面
     * @return
     */
    @RequestMapping("login_out")
    @GetMapping("/")
    public String login_out(HttpSession session){
        session.invalidate();//关闭会话
        return "login";
    }
}
