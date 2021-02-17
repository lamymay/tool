package com.arc.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yechao
 * @since 2020/7/7 9:29 上午
 */
public class MapTest {

    static Map map = new HashMap(16);

    static {
        map.put("1", "还没打卡？红包要飞走了！");
        map.put("2", "再不打卡，红包要飞走了!");
        map.put("3", "太可惜，您错过了昨日步数红包");
        map.put("41", "今日步数已达兑换标准");
        map.put("42", "[打款提醒] 今日步数已满");
        map.put("43", "棒！今日步数已达标→");
        map.put("44", "今天的无门槛红包来啦!");
        map.put("45", "1000步小目标已达成~");
        map.put("46", "辛苦攒的步数别忘了兑!");
        map.put("47", "你有步数红包待领取");
        map.put("5", "%s期红包赛已开奖");
        map.put("6", "%s期早起挑战已开奖");
        map.put("71", "开奖啦！步数红包别忘领");
        map.put("72", "已走路换到%s元");
        map.put("8", "今日步数红包可翻倍啦!");
        map.put("9", "翻倍成功！查收你的红包");
        map.put("10", "您有%s卡币未领取");
        map.put("11", "您的步数红包今晚将过期");
    }



}
