package com.accp.biz;

import com.accp.entity.Stock;
import com.accp.util.Pager;

public interface StockBiz {
    /**
     * 按条件查询供货渠道分页
     * @return
     */
    public Pager<Stock> findStock(Integer pageNo, Integer pageSize, Integer cmId,
                                    Integer clId, Integer fId);

    /**
     * 以ID查询
     * @param id
     * @return
     */
    public Stock findId(Integer id);

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
    public int del(Integer id);

    /**
     * 修改
     * @param stock
     * @return
     */
    public int update(Stock stock);
}
