package com.arc.test.date.json;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yechao
 * @since 2020/6/29 10:24 上午
 */
public class JSONTest {

    /**
     * map的key
     */
    public static final String USER_ID = "userId";

    /**
     * 活动实例编码
     */
    public static final String INSTANCE_CODE = "instanceCode";

    /**
     * 兑奖码s
     */
    public static final String TICKET_LIST = "redemptionCodes";

    public static void main(String[] args) {
        JSONObject map = new JSONObject();
        String userId = "ABSSJD";
        String instanceCode = CodeUtil.createCode();
        ArrayList<String> redemptionCodes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            redemptionCodes.add(CodeUtil.initRedemptionCode() + "");
        }
        map.put(USER_ID, userId);
        map.put(INSTANCE_CODE, instanceCode);
        map.put(TICKET_LIST, redemptionCodes);
        testJsonObject(map);

    }

    private static void testJsonObject(JSONObject map) {
        String userId = (String) map.get(USER_ID);
        String instanceCode = (String) map.get(INSTANCE_CODE);
        List<String> redemptionCodes = (List<String>) map.get(TICKET_LIST);

        System.out.println(userId);
        System.out.println(instanceCode);
        System.out.println(redemptionCodes);

    }
}
