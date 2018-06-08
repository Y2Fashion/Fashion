package com.accp.biz.impl;

import com.accp.biz.SupplierBiz;
import com.accp.dao.SupplierDao;
import com.accp.entity.Supplier;
import com.accp.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SupplierBizImpl implements SupplierBiz {
    @Resource
    private SupplierDao supplierDao;
    @Override
    public Pager<Supplier> findAll(Integer pageNo, Integer pageSize) {
        Pager<Supplier> pager=new Pager<Supplier>();
        if(null==pageNo){
            pageNo=1;
        }
        int num=(supplierDao.findCout()+pageSize-1)/pageSize;
        if(pageNo>num&&num!=0){
            pageNo=num;
        }
        pager.setPageNo(pageNo);
        pager.setPageSize(pageSize);
        pager.setDatas(supplierDao.findAll((pageNo-1)*pageSize,pageSize));
        pager.setTotalPage(num);
        pager.setTotalRows(supplierDao.findCout());
        return pager;
    }

    @Override
    public Supplier findById(Integer ID) {
        return supplierDao.findById(ID);
    }

    @Override
    public int Add(Supplier supplier) {
        return supplierDao.Add(supplier);
    }

    @Override
    public int Update(Supplier supplier) {
        return supplierDao.Update(supplier);
    }

    @Override
    public int Del(Integer ID) {
        return supplierDao.Del(ID);
    }

    @Override
    public List<Supplier> findSupplier() {
        return supplierDao.findSupplier();
    }
}
