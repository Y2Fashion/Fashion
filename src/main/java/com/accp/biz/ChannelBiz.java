package com.accp.biz;

import com.accp.entity.Channel;
import com.accp.util.Pager;

import java.util.List;

public interface ChannelBiz {
    /**
     * 按条件查询供货渠道分页
     * @return
     */
    public Pager<Channel> findChannel(Integer pageNo, Integer pageSize, Integer cmId,
                                      Integer clId,Integer fId);
    /**
     * 增加
     * @param channel
     * @return
     */
    public int Add(Channel channel);
    /**
     * 删除
     * @param id
     * @return
     */
    public int del(Integer id);
}
