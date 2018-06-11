package com.accp.biz.impl;

import com.accp.biz.ChannelBiz;
import com.accp.dao.ChannelDao;
import com.accp.entity.Channel;
import com.accp.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ChanelBizImpl implements ChannelBiz {
    @Resource
    private ChannelDao channelDao;
    @Override
    public Pager<Channel> findChannel(Integer pageNo, Integer pageSize ,Integer cmId,Integer clId,Integer fId)
    {
        if(null==pageNo){
            pageNo=1;
        }
        int num=(channelDao.findCount(cmId,clId,fId)+pageSize-1)/pageSize;
        if(pageNo>num){
            pageNo=num;
        }
        Pager<Channel> pager=new Pager<Channel>();
        pager.setPageNo(pageNo);
        pager.setPageSize(pageSize);
        pager.setDatas(channelDao.findChannel((pageNo-1)*pageSize,pageSize,cmId,clId,fId));
        pager.setTotalPage(num);
        pager.setTotalRows(channelDao.findCount(cmId,clId,fId));
        return pager;
    }

    @Override
    public int Add(Channel channel) {
        return channelDao.Add(channel);
    }

    @Override
    public int del(Integer id) {
        return channelDao.del(id);
    }
}
