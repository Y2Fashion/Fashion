package com.accp.biz.impl;

import com.accp.biz.Smbms_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class Smbms_UserServiceImpl implements Smbms_UserService {
    @Autowired
    private Smbms_UserDao sud;
    /*
     * 验证用户名与密码是否一致
     * @param userCode  用户名
     * @param userPassword 密码
     * @return 返回用户编号
     */
    public Integer list(String userCode,String userPassword) {
        try {
            int id= sud.select(userCode,userPassword);
            return id;
        }catch(Exception e){
            return 0;
        }
    }
}
