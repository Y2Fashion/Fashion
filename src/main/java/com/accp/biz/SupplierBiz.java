package com.accp.biz;

import com.accp.entity.Supplier;
import com.accp.util.Pager;

import java.util.List;

public interface SupplierBiz {
    /**
     * 查询并分页
     * @return
     */
    public Pager<Supplier> findAll(Integer pageNo, Integer pageSize);
    /**
     * 查询单个
     * @return
     */
    public Supplier findById(Integer ID);
    /**
     * 添加供应商
     * @param supplier
     * @return
     */
    public int Add(Supplier supplier);
    /**
     * 修改
     * @param supplier
     * @return
     */
    public int Update(Supplier supplier);

    /**
     * 删除
     * @param ID
     * @return
     */
    public int Del(Integer ID);
    /**
     * 查询供应商
     * @return
     */
    public List<Supplier> findSupplier();
}
