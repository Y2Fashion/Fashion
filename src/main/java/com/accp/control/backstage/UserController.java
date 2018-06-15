package com.accp.control.backstage;

import com.accp.biz.UserBiz;
import com.accp.entity.User;
import com.accp.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/***
 * 后台用户管理
 */
@Controller
public class UserController {
    @Resource
    private UserBiz userBiz;
    /**
     * 用户管理页面
     * 因为是刚进入，是查询全部
     */
    @RequestMapping("user")
    @GetMapping("/")
    public String user_list(Model model,HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        User user=(User)session.getAttribute("user_role");
        Pager<User> list=null;
        if(user.getUserRole().equals("超级管理员")||user.getUserRole().equals("管理员")){
            list=userBiz.list(new User(),1);
        }else{
            list=userBiz.list(user);
        }
        model.addAttribute("role","");
        model.addAttribute("sex","");
        model.addAttribute("state","");
        model.addAttribute("pager",list);
        model.addAttribute("user_role",user);
        return "/backstage/user";
    }

    /**
     * 上一页，下一页，根据条件查询
     * @param model
     * @param role  用户角色
     * @param sex   用户性别
     * @param state 用户状态
     * @param num   单前页数
     * @return
     */
    @RequestMapping("user_pager")
    @GetMapping("/")
    public String user_pager(Model model,String role,String sex,String state,Integer num,HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        User users=(User)session.getAttribute("user_role");
        User user=new User();
        user.setUserRole(role);
        user.setUserSex(sex);
        user.setUserState(state);
        Pager<User> list=userBiz.list(user,num);
        model.addAttribute("role",role);
        model.addAttribute("sex",sex);
        model.addAttribute("state",state);
        model.addAttribute("pager",list);
        model.addAttribute("user_role",users);
        return "/backstage/user";
    }

    /***
     * 批量删除所选中的用户
     * @param arr 删除的用户ID数组
     * @return
     */
    @RequestMapping("user_dels")
    @ResponseBody
    @GetMapping("/")
    public String user_dels(@RequestParam(value = "arr[]") Integer[] arr,HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        boolean yesNo=userBiz.removes(arr);
        return "redirect:/user";
    }

    /**
     * 删除单个用户
     * @return
     */
    @RequestMapping("user_del")
    @ResponseBody
    @GetMapping("/")
    public String user_del(Integer id,HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        boolean yesNo=userBiz.remove(id);
        return "redirect:/user";
    }

    /**
     * 根据用户ID修改 用户状态
     * @param id 用户ID
     * @param state  用户状态
     */
    @RequestMapping("user_upd_state")
    @ResponseBody
    @GetMapping("/")
    public void upd_State(Integer id,String state){
        userBiz.upd_User(new User(id,state));
    }

    /***
     * 添加用户
     * @return
     */
    @RequestMapping("user_add")
    @GetMapping("/")
    public String user_add(Model model,User user,HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        if(user.getUserName()==null){
            return "/backstage/user_management_add";
        }else{
            if(userBiz.add_User(user)){
                model.addAttribute("add_err","成功");
            }
            return "/backstage/user_management_add";
        }
    }

    /***
     * 进入修改密码页面
     * @return
     */
    @RequestMapping("user_upd_pwd")
    @GetMapping("/")
    public String user_pwd(Model model,Integer id,HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        User user=userBiz.getById(id);
        session.setAttribute("ppd",user.getUserPwd());
        user.setUserPwd("");//密码就不保存在model中一起发送到客服端
        model.addAttribute("user_ById",user);
        return "/backstage/user_management_password";
    }

    /**
     * 判断旧密码是否正确
     * @param model
     * @param pwd
     * @param session
     * @return
     */
    @RequestMapping("pwd_pwd")
    @ResponseBody
    @GetMapping("/")
    public String pwd_Pwd(Model model,String pwd , HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        String ppd=session.getAttribute("ppd").toString();
        if(ppd.equals(pwd)){
            return "true";
        }else{
            return "false";
        }
    }

    /**
     * 修改密码提交
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("upd_pwd")
    @GetMapping("/")
    public String upd_Pwd(Model model,User user, HttpSession session){
        if(session.getAttribute("user_role")==null){
            return "redirect:/login";
        }
        if(userBiz.upd_User(user)){
            session.removeAttribute("ppd");//清空密码
            model.addAttribute("add_err","成功");
            model.addAttribute("user_ById",user);
            return "/backstage/user_management_password";
        }else{
            user.setUserPwd("");//密码就不保存在model中一起发送到客服端
            model.addAttribute("user_ById",user);
            return "/backstage/user_management_password";
        }
    }
}
