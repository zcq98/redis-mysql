package com.zcq.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkInfoMapper {
    int addLinkInfo(@Param("linkid") String linkid, @Param("value") double value);
}
