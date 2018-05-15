package com.accp.biz;

public interface Smbms_UserService {
	/*
	 * 验证用户名与密码是否一致
	 * @param userCode  用户名
	 * @param userPassword 密码
	 * @return 返回用户编号
	 */
	Integer list(String userCode, String userPassword);
}
