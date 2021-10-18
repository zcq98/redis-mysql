package com.zcq.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HighlightLinkMapper {
    int addHighlightLink(@Param("linkid") String linkid, @Param("flag") int flag);
}
