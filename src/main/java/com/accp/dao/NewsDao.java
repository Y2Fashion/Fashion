package com.accp.dao;

import com.accp.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsDao {
    /**
     * 分类查询新闻并分页
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<News> findNew(@Param("type") String type, @Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    /**
     * 分类查询新闻条数
     * @param type
     * @return
     */
    public int findCout(@Param("type") String type);

    /**
     * 查询新闻类型
     * @return
     */
    public List<News> findType();
}
