package com.accp.dao;

import com.accp.entity.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockDao {
    /**
     * 查询分页
     * @param pageNo
     * @param pageSize
     * @param cmId
     * @param clId
     * @param fId
     * @return
     */
    public List<Stock> findStock(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize,
                                 @Param("cmId")Integer cmId,
                                 @Param("clId")Integer clId, @Param("fId")Integer fId);

    /**
     * 查询条数
     * @return
     */
    public int findCount(@Param("cmId")Integer cmId,
                         @Param("clId")Integer clId,@Param("fId")Integer fId);

    /**
     * 以ID查询
     * @param id
     * @return
     */
    public Stock findId(@Param("id")Integer id);

    /**
     * 增加
     * @param stock
     * @return
     */
    public int add(Stock stock);

    /**
     * 删除
     * @param id
     * @return
     */
    public int del(@Param("id")Integer id);

    /**
     * 修改
     * @param stock
     * @return
     */
    public int update(Stock stock);
}
