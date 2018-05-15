package com.accp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.accp.entity.Smbms_User;

/**
 * 用户信息表
 */
public interface Smbms_UserDao {
	/**
	 * 验证用户名与密码是否一致
	 * @param userCode  用户名
	 * @param userPassword 密码
	 * @return
	 */
	Integer select(@Param("userCode") String userCode,
                   @Param("userPassword") String userPassword);
}
