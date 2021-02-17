package com.arc.test.random.test2;

/**
 * @author yechao
 * @since 2020/6/23 1:49 下午
 */
public class LotteryItem {
    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 中奖几率
     */
    private Double awardProbability;
    /**
     * 奖品中奖数字范围起点
     */
    private Integer awardStartCode;
    /**
     * 奖品中奖数字范围终点
     */
    private Integer awardEndCode;

    /**
     * 中奖数字，实际应用可不定义。
     * 此处定义是为了方便读者理解
     */
    private Integer awardCode;

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }
    public Double getAwardProbability() {
        return awardProbability;
    }

    public void setAwardProbability(Double awardProbability) {
        this.awardProbability = awardProbability;
    }

    public Integer getAwardStartCode() {
        return awardStartCode;
    }

    public void setAwardStartCode(Integer awardStartCode) {
        this.awardStartCode = awardStartCode;
    }

    public Integer getAwardEndCode() {
        return awardEndCode;
    }

    public void setAwardEndCode(Integer awardEndCode) {
        this.awardEndCode = awardEndCode;
    }

    public Integer getAwardCode() {
        return awardCode;
    }

    public void setAwardCode(Integer awardCode) {
        this.awardCode = awardCode;
    };
}