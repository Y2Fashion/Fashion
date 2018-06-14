package com.accp.dao;

import com.accp.entity.Channel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChannelDao {
    /**
     * 查询供货渠道
     * @return
     */
    public List<Channel> findChannel(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,
                                     @Param("cmId")Integer cmId,
                                     @Param("clId")Integer clId,@Param("fId")Integer fId
                                     );

    /**
     * 查询条数
     * @return
     */
    public int findCount(@Param("cmId")Integer cmId,
                         @Param("clId")Integer clId,@Param("fId")Integer fId);

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
    public int del(@Param("id")Integer id);
}
