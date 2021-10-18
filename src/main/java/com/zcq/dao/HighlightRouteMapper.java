package com.zcq.dao;

import com.zcq.pojo.HighlightRoute;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HighlightRouteMapper {
    int addHighlightRoute(@Param("mac") String mac, @Param("highlightRoute") HighlightRoute highlightRoute);

    int deleteAll();
}
