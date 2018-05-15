package com.accp.dao;

import java.util.List;

import com.accp.entity.Smbms_role;

/**
 * 用户角色表
 */
public interface Smbms_roleDao {
	/***
	 * 查询全部角色信息
	 * @return
	 */	
	List<Smbms_role> select();
	/**
	 * 根据角色ID 修改角色信息
	 * @param smbms_role
	 * @return
	 */
	int update(Smbms_role smbms_role);
	/**
	 * 添加新角色
	 * @param smbms_role
	 * @return
	 */
	int insert(Smbms_role smbms_role);
}
