package com.accp.dao;

import com.accp.entity.Lining;

import java.util.List;

public interface LiNingDao {

    Lining selecttLiNingById(String id);
    List<Lining> selectLiNingList();
}
