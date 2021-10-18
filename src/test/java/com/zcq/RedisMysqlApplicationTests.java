package com.zcq;

import com.zcq.dao.*;
import com.zcq.pojo.FlowInfo;
import com.zcq.pojo.HighlightRoute;
import com.zcq.pojo.SwitchInfo;
import com.zcq.utils.ParseRedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RedisMysqlApplicationTests {
    @Autowired
    private ParseRedisUtil parseRedisUtil;

    @Autowired
    private FlowInfoMapper flowInfoMapper;
    @Autowired
    private SwitchInfoMapper switchInfoMapper;
    @Autowired
    private LinkInfoMapper linkInfoMapper;
    @Autowired
    private HighlightRouteMapper highlightRouteMapper;
    @Autowired
    private HighlightLinkMapper highlightLinkMapper;

    //@Test
    void db2() {
        Map<String, SwitchInfo> dynamicDataShow = parseRedisUtil.Mapvalue2Bean(2, SwitchInfo.class, "dynamicDataShow");
        for (String s : dynamicDataShow.keySet()) {
            if (switchInfoMapper.addSwitchInfo(s, dynamicDataShow.get(s)) > 0) {
                System.out.println(s + " : " + dynamicDataShow.get(s));
            }
        }
    }

    //@Test
    void db7() {
        Map<String, String> stringStringMap = parseRedisUtil.value2String(7);
        for (String s : stringStringMap.keySet()) {
            if (linkInfoMapper.addLinkInfo(s, Double.parseDouble(stringStringMap.get(s))) > 0) {
                System.out.println(s + " : " + stringStringMap.get(s));
            }
        }
    }

    //@Test
    void db9() {
        Map<String, FlowInfo> stringStringMap = parseRedisUtil.Jsonvalue2Bean(9, FlowInfo.class);
        for (String s : stringStringMap.keySet()) {
            String[] split = s.split(">");
            if (split[0].toCharArray()[2] == ':') {
                System.out.println("上行链路");
            }
            System.out.println(split[0] + " " + split[1]);
            System.out.println(s + " : " + stringStringMap.get(s));
        }
    }

    //@Test
    void db8() {
        Map<String, HighlightRoute> stringStringMap = parseRedisUtil.Jsonvalue2Bean(8, HighlightRoute.class);
        Map<String, Integer> highlightLink = new HashMap<>();
        int flag = 1;
        for (String s : stringStringMap.keySet()) {
            highlightRouteMapper.addHighlightRoute(s, stringStringMap.get(s));
            String[] split = stringStringMap.get(s).getPath().split(", ");
            for (int i = 0; i < split.length - 1; i++) {
                if (Integer.parseInt(split[i]) <= Integer.parseInt(split[i + 1])) {
                    highlightLink.put(split[i] + "-" + split[i + 1], flag);
                } else {
                    highlightLink.put(split[i + 1] + "-" + split[i], flag);
                }
            }
            flag++;
            System.out.println(s + " : " + stringStringMap.get(s));
        }
        for (String s : highlightLink.keySet()) {
            highlightLinkMapper.addHighlightLink(s, highlightLink.get(s));
            System.out.println(s + " : " + highlightLink.get(s));
        }
        //highlightRouteMapper.deleteAll();
        //highlightLinkMapper.deleteAll();
    }

}
