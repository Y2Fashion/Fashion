package com.accp.biz;

import com.accp.entity.Picture;

import java.util.List;

public interface PictureBiz {
    List<Picture> getPictureList(Object cId);
}
