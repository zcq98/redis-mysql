package com.zcq.schedule;

import com.github.it235.manager.Knife4jRedisManager;
import com.github.it235.util.RedisHashUtil;
import com.github.it235.util.RedisListUtil;
import com.zcq.dao.*;
import com.zcq.pojo.FlowInfo;
import com.zcq.pojo.HighlightRoute;
import com.zcq.pojo.MsgQueue;
import com.zcq.pojo.SwitchInfo;
import com.zcq.utils.ParseRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 解析存储Redis数据到HashMap
 * @author: congqi.zhao
 * @date: Created in 2021/9/8 15:21
 */
@Component
public class ParseRedisTask {
    @Autowired
    private ParseRedisUtil parseRedisUtil;

    @Autowired
    private RedisHashUtil redisHashUtil;
    @Autowired
    private Knife4jRedisManager knife4jRedisManager;

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
    @Autowired
    private ConfidenceMapper confidenceMapper;
    @Autowired
    private MsgQueueMapper msgQueueMapper;

    private static HashMap<String, Integer> hashMap;
    private static String message;

    static {
        hashMap = new HashMap<>();
        String str = "0-1: k0adza7aL02ZU83VdvUm-190\n" +
                "0-2: k0adza7aL02ZU83VdvUm-192\n" +
                "0-3: k0adza7aL02ZU83VdvUm-193\n" +
                "0-4: k0adza7aL02ZU83VdvUm-194\n" +
                "0-10: k0adza7aL02ZU83VdvUm-195\n" +
                "1-3: k0adza7aL02ZU83VdvUm-197\n" +
                "1-4: k0adza7aL02ZU83VdvUm-198\n" +
                "1-5: k0adza7aL02ZU83VdvUm-200\n" +
                "1-15: k0adza7aL02ZU83VdvUm-196\n" +
                "2-6: k0adza7aL02ZU83VdvUm-201\n" +
                "2-7: k0adza7aL02ZU83VdvUm-202\n" +
                "3-6: k0adza7aL02ZU83VdvUm-203\n" +
                "3-7: k0adza7aL02ZU83VdvUm-204\n" +
                "4-8: k0adza7aL02ZU83VdvUm-205\n" +
                "4-9: k0adza7aL02ZU83VdvUm-206\n" +
                "5-8: k0adza7aL02ZU83VdvUm-207\n" +
                "5-9: k0adza7aL02ZU83VdvUm-208\n" +
                "6-14: k0adza7aL02ZU83VdvUm-224\n" +
                "7-20: k0adza7aL02ZU83VdvUm-225\n" +
                "8-21: k0adza7aL02ZU83VdvUm-226\n" +
                "9-18: k0adza7aL02ZU83VdvUm-223\n" +
                "10-11: k0adza7aL02ZU83VdvUm-209\n" +
                "10-12: k0adza7aL02ZU83VdvUm-210\n" +
                "11-12: k0adza7aL02ZU83VdvUm-211\n" +
                "11-13: k0adza7aL02ZU83VdvUm-215\n" +
                "11-14: k0adza7aL02ZU83VdvUm-213\n" +
                "13-14: k0adza7aL02ZU83VdvUm-214\n" +
                "12-14: k0adza7aL02ZU83VdvUm-212\n" +
                "15-16: k0adza7aL02ZU83VdvUm-216\n" +
                "15-17: k0adza7aL02ZU83VdvUm-217\n" +
                "16-17: k0adza7aL02ZU83VdvUm-218\n" +
                "16-18: k0adza7aL02ZU83VdvUm-220\n" +
                "17-18: k0adza7aL02ZU83VdvUm-222\n" +
                "17-19: k0adza7aL02ZU83VdvUm-221\n" +
                "18-19: k0adza7aL02ZU83VdvUm-219\n" +
                "20-22: k0adza7aL02ZU83VdvUm-230\n" +
                "20-23: k0adza7aL02ZU83VdvUm-228\n" +
                "21-22: k0adza7aL02ZU83VdvUm-227\n" +
                "21-23: k0adza7aL02ZU83VdvUm-229\n" +
                "22-24: k0adza7aL02ZU83VdvUm-231\n" +
                "23-24: k0adza7aL02ZU83VdvUm-232\n";
        String[] split = str.split("\n");
        for (String s : split) {
            String[] split1 = s.split(": ");
            hashMap.put(split1[0], 0);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void loopdb2() {
        Map<String, SwitchInfo> dynamicDataShow = parseRedisUtil.Mapvalue2Bean(2, SwitchInfo.class, "dynamicDataShow");
        for (String s : dynamicDataShow.keySet()) {
            switchInfoMapper.addSwitchInfo(s, dynamicDataShow.get(s));
        }
    }

    @Scheduled(fixedRate = 5000)
    public void loopdb5() {
        int id = 1;
        confidenceMapper.deleteAll();
        Map<String, Double> confidence = redisHashUtil.getMapAll(5, "confidence", String.class, Double.class);
        for (String s : confidence.keySet()) {
            String[] split = s.split("-");
            confidenceMapper.addConfidence("业务流" + id, split[0], split[1], confidence.get(s));
            id++;
        }
    }

    @Scheduled(fixedRate = 5000)
    public void loopdb6() {
        Object value = knife4jRedisManager.redisTemplate(6).opsForList().rightPop("msgQueue");
        if (value != null) {
            String[] split = (value + "").split(",");
            String[] mac_ip = split[1].split("-");
            message = "用户" + mac_ip[0] + "信任度太低，访问资源" + mac_ip[1] + "的权限不足,连接即将中断";
            msgQueueMapper.addMessage(split[0], message);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void loopdb7() {
        Map<String, String> stringStringMap = parseRedisUtil.value2String(7);
        for (String s : stringStringMap.keySet()) {
            linkInfoMapper.addLinkInfo(s, Double.parseDouble(stringStringMap.get(s)));
        }
    }

    @Scheduled(fixedRate = 5000)
    public void loopdb8() {
        highlightRouteMapper.deleteAll();
        for (String s : hashMap.keySet()) {
            highlightLinkMapper.addHighlightLink(s, hashMap.get(s));
        }
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
        }
        for (String s : highlightLink.keySet()) {
            highlightLinkMapper.addHighlightLink(s, highlightLink.get(s));
        }
    }

    @Scheduled(fixedRate = 5000)
    public void loopdb9() {
        Map<String, FlowInfo> stringStringMap = parseRedisUtil.Jsonvalue2Bean(9, FlowInfo.class);
        String[] split;
        flowInfoMapper.deleteAll();
        for (String s : stringStringMap.keySet()) {
            split = s.split(">");
            if (split[0].toCharArray()[2] == ':') {
                flowInfoMapper.addFlowInfo(s, split[0], split[1], 0, stringStringMap.get(s));
            } else {
                flowInfoMapper.addFlowInfo(s, split[1], split[0], 1, stringStringMap.get(s));
            }
        }
    }

}
