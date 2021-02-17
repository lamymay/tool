package com.arc.test.date.json;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author yechao
 * @since 2020/5/10 2:54 下午
 */
public class CodeUtil {

    /**
     * 夺宝码的取值范围
     */
    public static String STR = "123456789";

    /**
     * 夺宝码的长度
     */
    public static int INIT_REDEMPTION_CODE_LENGTH = 8;

    /**
     * 创建一个随机的字符串的code length=16
     *
     * @return 创建一个随机的字符串的code
     */
    public static String createCode() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16).toUpperCase();
    }

    public static long createRandomPK(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    /**
     * 创建一个随机的字符串的code length=32
     *
     * @return 创建一个随机的字符串的code
     */
    public static String createCodeLength32() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static String createAliUid() {
        String codeLength32 = createCodeLength32();
        return "U" + codeLength32.substring(0, codeLength32.length() - 1);
    }

    /**
     * @return 获取初始兑奖码 8位 有概率重复
     */
    public static int initRedemptionCode() {
        return Integer.valueOf(RandomStringUtils.random(INIT_REDEMPTION_CODE_LENGTH, STR));
    }

    public static final String mosaic = "*";

    public static String addMosaicNickname(String nickname) {
        if (nickname == null|| StringUtils.isBlank(nickname)) {
            return mosaic+mosaic;
        } else if (nickname.length() ==1) {
            return nickname+mosaic;
        } else if (nickname.length() ==2) {
            return nickname.substring(0,1)+mosaic;
        } else {
            int length = nickname.length();
            int tempLength = length - 2;
            int userNickTruncateLength=10;
            if (tempLength > userNickTruncateLength) {
                tempLength = userNickTruncateLength;
            }
            int i = 0;
            StringBuffer buffer = new StringBuffer();
            while (i<tempLength){
                buffer.append(mosaic);
                ++i;
            }
            return nickname.substring(0,1)+buffer.toString()+nickname.substring(length-1,length);
        }
    }

    public static void main(String[] args) {
        System.out.println(createAliUid());
        System.out.println(createAliUid());
        System.out.println(createAliUid());
        System.out.println(createAliUid());

        System.out.println(DateUtils.addHours(new Date(), -1));
        System.out.println(DateUtils.addHours(new Date(), -10));

        String nickname = "A";
        System.out.println(nickname.substring(0, 1));
        System.out.println("首字母="+nickname.substring(0, 1));
        System.out.println("尾字母="+nickname.substring(nickname.length()-1, nickname.length()));

        nickname = "AB";
        System.out.println(nickname.substring(0, 1));
        System.out.println("首字母="+nickname.substring(0, 1));
        System.out.println("尾字母="+nickname.substring(nickname.length()-1, nickname.length()));


        nickname = "ABC";
        System.out.println(nickname.substring(0, 1));

        System.out.println("首字母="+nickname.substring(0, 1));
        System.out.println("尾字母="+nickname.substring(nickname.length()-1, nickname.length()));

        nickname = "ABCD";
        System.out.println(nickname.substring(0, 1));
        System.out.println("首字母="+nickname.substring(0, 1));
        System.out.println("尾字母="+nickname.substring(nickname.length()-1, nickname.length()));


        System.out.println(addMosaicNickname(null));
        System.out.println(addMosaicNickname(""));
        System.out.println(addMosaicNickname("   "));
        System.out.println(addMosaicNickname("张"));
        System.out.println(addMosaicNickname("张三"));
        System.out.println(addMosaicNickname("张三哈"));
        System.out.println(addMosaicNickname("赵子龙"));
        System.out.println(addMosaicNickname("撒豆腐阿斯达"));
        System.out.println(addMosaicNickname("撒豆腐阿大师傅法撒旦是非得失而又特容易斯达"));
    }


}
