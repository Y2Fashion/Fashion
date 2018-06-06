package com.accp.dao;

import com.accp.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierDao {

    /**
     * 查询所有并分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Supplier> findAll(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    /**
     * 查询总条数
     * @return
     */
    public int findCout();

    /**
     * 查询单个
     * @return
     */
    public Supplier findById(@Param("ID") Integer ID);

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
    public int Del(@Param("ID")Integer ID);

    /**
     * 查询供应商
     * @return
     */
    public List<Supplier> findSupplier();
}
