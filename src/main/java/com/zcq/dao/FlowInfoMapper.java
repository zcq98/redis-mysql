package com.zcq.dao;

import com.zcq.pojo.FlowInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowInfoMapper {
    int addFlowInfo(@Param("flowid") String flowid, @Param("mac") String mac, @Param("ip") String ip, @Param("flag") int flag, @Param("flowInfo") FlowInfo flowInfo);

    int deleteAll();
}
