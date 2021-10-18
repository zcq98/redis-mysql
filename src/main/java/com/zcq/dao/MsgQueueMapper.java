package com.zcq.dao;

import com.zcq.pojo.MsgQueue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgQueueMapper {
    int addMessage(@Param("time") String time, @Param("message") String message);
}