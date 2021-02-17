package com.arc.test.random.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yechao
 * @since 2020/6/23 1:49 下午
 */
public class LotteryUtils {
    private static final Random random = new Random();
    private static final Integer MAXSOPE = 100000000;

    public static void calAwardProbability(Lottery lottery, List<LotteryItem> lotteryItemList) {
        Integer codeScope = 1;
        for (LotteryItem item : lotteryItemList) {
            Integer nowScope = 1;
            Double awardProbability = item.getAwardProbability();
            while (true) {
                Double test = awardProbability * nowScope;
                // 概率的精确度，调整到小数点后10位，概率太小等于不中奖，跳出
                if (test < 0.0000000001) {
                    break;
                }
                if ((test >= 1L && (test - test.longValue()) < 0.0001D) || nowScope >= MAXSOPE) {
                    if (nowScope > codeScope) {
                        // 设置中奖范围
                        codeScope = nowScope;
                    }
                    break;
                } else {
                    // 中奖数字范围以10倍进行增长
                    nowScope = nowScope * 10;
                }
            }
        }
        Integer winningStartCode = 0;
        Integer winningEndCode = winningStartCode;

        for (LotteryItem item : lotteryItemList) {
            Integer codeNum = (int) (item.getAwardProbability() * codeScope); // 获得其四舍五入的整数值
            // 无人中奖时，将中奖的起始范围设置在随机数的范围之外
            if (codeNum == 0) {
                item.setAwardStartCode(codeScope + 1);
                item.setAwardEndCode(codeScope + 1);
            } else {
                item.setAwardStartCode(winningEndCode);
                item.setAwardEndCode(winningEndCode + codeNum - 1);
                winningEndCode = winningEndCode + codeNum;
            }
        }
        // 设置用户的中奖随机码信息
        lottery.setWinningStartCode(winningStartCode);
        lottery.setWinningEndCode(winningEndCode);
        lottery.setCodeScope(codeScope);
    }

    public static LotteryItem beginLottery(Lottery lottery, List<LotteryItem> lotteryItemList) {
        // 确定活动是否有效,如果活动无效则，直接抽奖失败
        Integer randomCode = random.nextInt(lottery.getCodeScope());
        if (randomCode >= lottery.getWinningStartCode() && randomCode <= lottery.getWinningEndCode()) {
            for (LotteryItem item : lotteryItemList) {
                if (randomCode >= item.getAwardStartCode() && randomCode <= item.getAwardEndCode()) {
                    item.setAwardCode(randomCode);
                    return item;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<LotteryItem> lotteryItemList = new ArrayList<LotteryItem>();
        LotteryItem awardItem1 = new LotteryItem();
        awardItem1.setAwardName("红包10元");
        awardItem1.setAwardProbability(0.25D);
        lotteryItemList.add(awardItem1);

        LotteryItem awardItem2 = new LotteryItem();
        awardItem2.setAwardName("红包20元");
        awardItem2.setAwardProbability(0.25D);
        lotteryItemList.add(awardItem2);

        LotteryItem awardItem3 = new LotteryItem();
        awardItem3.setAwardName("谢谢参与");
        awardItem3.setAwardProbability(0.5D);
        lotteryItemList.add(awardItem3);

        Lottery lottery = new Lottery();
        LotteryUtils.calAwardProbability(lottery, lotteryItemList);
        System.out.println("抽奖活动中奖数字范围：["+lottery.getWinningStartCode()+","+lottery.getWinningEndCode()+")");
        LotteryUtils.beginLottery(lottery, lotteryItemList);
        for (LotteryItem item : lotteryItemList) {
            System.out.println(item.getAwardName()+" 中奖数字范围：["+item.getAwardStartCode()+","+item.getAwardEndCode()+"]");
        }
        System.out.println("以下是模拟的抽奖中奖结果：");
        LotteryItem award1 = LotteryUtils.beginLottery(lottery, lotteryItemList);
        System.out.println("抽中的数字是："+award1.getAwardCode()+",恭喜中奖："+award1.getAwardName()+",数字落点["+award1.getAwardStartCode()+","+award1.getAwardEndCode()+"]");
        LotteryItem award2 = LotteryUtils.beginLottery(lottery, lotteryItemList);
        System.out.println("抽中的数字是："+award2.getAwardCode()+",恭喜中奖："+award2.getAwardName()+",数字落点["+award2.getAwardStartCode()+","+award2.getAwardEndCode()+"]");
        LotteryItem award3 = LotteryUtils.beginLottery(lottery, lotteryItemList);
        System.out.println("抽中的数字是："+award3.getAwardCode()+",恭喜中奖："+award3.getAwardName()+",数字落点["+award3.getAwardStartCode()+","+award3.getAwardEndCode()+"]");
        LotteryItem award4 = LotteryUtils.beginLottery(lottery, lotteryItemList);
        System.out.println("抽中的数字是："+award4.getAwardCode()+",恭喜中奖："+award4.getAwardName()+",数字落点["+award4.getAwardStartCode()+","+award4.getAwardEndCode()+"]");
    }

}

//三、系统的过载保护
//https://blog.ityuan.com/486
//
//系统的过载保护目的是当流量超出预期时，自动过滤一部分流量，防止系统被拖垮。
//
//常用的过载保护思路，大多是基于漏桶算法思想或者信号量控制。
//
//例如：java自带的Semaphore 或者Google Guava
//
//Semaphore semaphore = new Semaphore(10);
// if (semaphore.tryAcquire()) {// （非阻塞式）
//     // 获得许可证才可进行下一步操作
//     // semaphore.acquire();（阻塞式）
//     // dos somethine
//    // 释放许可证
//    semaphore.release();
//}