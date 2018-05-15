package com.accp.biz;

import com.accp.entity.Smbms_role;

import java.util.List;

public interface Smbms_roleService {
    /***
     * 查询全部角色信息
     * @return
     */
    List<Smbms_role> list();
}
