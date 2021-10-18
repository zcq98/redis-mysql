package com.zcq.dao;

import com.zcq.pojo.SwitchInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SwitchInfoMapper {
    int addSwitchInfo(@Param("switchid") String switchid, @Param("switchInfo") SwitchInfo switchInfo);
}
