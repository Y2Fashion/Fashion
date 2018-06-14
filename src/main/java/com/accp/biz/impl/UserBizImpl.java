package com.accp.biz.impl;

import com.accp.biz.UserBiz;
import com.accp.dao.UserDao;
import com.accp.entity.User;
import com.accp.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/***
 * 用户表
 */
@Service
public class UserBizImpl implements UserBiz {
    @Resource
    private UserDao userDao;

    /***
     * 登录注册
     * @param user  用户名  密码   用户状态
     * @return  返回对象
     */
    @Override
    public User get(User user) {
        return userDao.select_get(user);
    }


    /***
     * 查询符合某条件的所有用户
     * @param user 某条件
     * @param num 当前页数
     * @return 返回集合
     */
    @Override
    public Pager<User> list(User user,int num) {
        Pager<User> pa=new Pager<User>();
        pa.setPageNo(num);//当前页数
        pa.setPageSize(8);//每页显示的条数
        pa.setTotalRows(userDao.select_sum(user));//总条数
        pa.setTotalPage((pa.getTotalRows()+8-1)/8);//总页数
        pa.setDatas(userDao.select(user.getUserRole(),user.getUserSex(),user.getUserState(),(num-1)*8,8));//存放业务集合
        return pa;
    }

    @Override
    public Pager<User> list(User user) {
        List<User> lis=new ArrayList<User>();
        user.setUserPwd("");
        lis.add(user);
        Pager<User> pa=new Pager<User>();
        pa.setPageNo(1);//当前页数
        pa.setPageSize(8);//每页显示的条数
        pa.setTotalRows(1);//总条数
        pa.setTotalPage(1);//总页数
        pa.setDatas(lis);//存放业务集合
        return pa;
    }


    /***
     * 符合某条件的用户条数
     * @return  返回集合
     */
    @Override
    public Integer get_sum(User user) {
        return userDao.select_sum(user);
    }

    /**
     * 批量删除用户
     * @param id  用户ID的数组
     * @return
     */
    @Override
    public boolean removes(Integer[] id) {
        return userDao.remove_user(id)>0;
    }

    /**
     * 删除单个用户
     * @param id  用户ID
     * @return
     */
    public boolean remove(Integer id){
        Integer[] arr={id};
        return userDao.remove_user(arr)>0;
    }

    /***
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean upd_User(User user){
        return userDao.update_user(user)>0;
    }


    /**
     * 添加新用户
     * @param user
     * @return
     */
    public boolean add_User(User user){
        user.setUserTime(new Date());// new Date()为获取当前系统时间
        user.setUserState("已启用");
        return userDao.insert(user)>0;
    }

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    public User getById(Integer id){
        return userDao.select_get_id(id);
    }
}
