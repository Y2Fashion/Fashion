package com.accp.biz.impl;

import com.accp.biz.FigureBiz;
import com.accp.dao.FigureDao;
import com.accp.entity.Figure;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class FigureBizImpl implements FigureBiz {

    @Resource
    private FigureDao figureDao;

    @Override
    public List<Figure> getFigureList() {
        return figureDao.selectFigureList();
    }
}
