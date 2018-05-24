package com.accp.biz;

import com.accp.dao.LiNingDao;
import com.accp.entity.Lining;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


public interface LiNingBiz {
    /*
    * 按id查询面料
    * */
    Lining getLiNingById(Integer id);
    /*
    * 查询所有面料
    * */
    List<Lining> getLiNingList();
}
