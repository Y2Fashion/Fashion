package com.accp.biz;

import com.accp.entity.User;
import com.accp.util.Pager;

import java.util.List;

/***
 * 用户表
 */
public interface UserBiz {
    /***
     * 登录注册
     * @param user  用户名  密码   用户状态
     * @return  返回对象
     */
    User get(User user);

    /***
     * 查询符合某条件的所有用户
     * @param user 某条件
     * @param num 当前页数
     * @return 返回集合
     */
    Pager<User> list(User user, int num);

    Pager<User> list(User user);

    /***
     * 符合某条件的用户条数
     * @return  返回集合
     */
    Integer get_sum(User user);

    /**
     * 批量删除用户
     * @param id  用户ID的数组
     * @return
     */
    boolean removes(Integer[] id);

    /**
     * 删除单个用户
     * @param id  用户ID
     * @return
     */
    boolean remove(Integer id);

    /***
     * 修改用户信息
     * @param user
     * @return
     */
    boolean upd_User(User user);

    /**
     * 添加新用户
     * @param user
     * @return
     */
    boolean add_User(User user);

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    User getById(Integer id);
}
