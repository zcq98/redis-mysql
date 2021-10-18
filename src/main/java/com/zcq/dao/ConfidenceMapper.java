package com.zcq.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfidenceMapper {
    int addConfidence(@Param("id") String id, @Param("mac") String mac, @Param("ip") String ip, @Param("value") double value);

    int deleteAll();
}
