package com.accp.biz.impl;

import com.accp.biz.Smbms_roleService;
import com.accp.dao.Smbms_roleDao;
import com.accp.entity.Smbms_role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleServiceImpl")
public class Smbms_roleServiceImpl implements Smbms_roleService {

    @Autowired
    private Smbms_roleDao role;
    /***
     * 查询全部角色信息
     * @return
     */
   public List<Smbms_role> list(){
       return role.select();
   }
}
