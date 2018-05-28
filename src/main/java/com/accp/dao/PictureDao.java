package com.accp.dao;

import com.accp.entity.Picture;

import java.util.List;

public interface PictureDao {
    List<Picture> selectPictureList(Object cId);
}
