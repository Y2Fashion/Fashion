package com.accp.biz.impl;

import com.accp.biz.PictureBiz;
import com.accp.dao.PictureDao;
import com.accp.entity.Picture;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class PictureBizImpl implements PictureBiz {
    @Resource
    private PictureDao pictureDao;
    @Override
    public List<Picture> getPictureList(Object cId) {
        return pictureDao.selectPictureList(cId);
    }
}
