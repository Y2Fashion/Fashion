package com.accp.dao;

import com.accp.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 *用户表
 */
public interface UserDao {

    /***
     * 登录注册
     * @param user  用户名  密码   用户状态
     * @return  返回对象
     */
    User select_get(User user);

    /**
     * 查询符合某条件的所有用户  分页查寻
     * @param
     * @param num  起始位置
     * @param size 查询多少条
     * @return 返回集合
     */
    List<User> select( @Param("userRole") String userRole,
                      @Param("userSex") String userSex, @Param("userState") String userState,
                      @Param("num") int num , @Param("size") int size);

    /***
     * 符合某条件的用户条数
     * @return  返回总条数
     */
    Integer select_sum(User user);

    /**
     * 批量删除用户
     * @param user_id  用户ID的数组
     * @return
     */
    Integer remove_user(Integer[] user_id);

    /***
     * 修改用户信息
     * @param user
     * @return
     */
    Integer update_user(User user);

    /**
     * 添加新用户
     * @param user
     * @return
     */
    Integer insert(User user);

    /***
     * 根据ID查询用户信息
     * @param id  用户名id
     * @return  返回对象
     */
    User select_get_id(Integer id);
}
