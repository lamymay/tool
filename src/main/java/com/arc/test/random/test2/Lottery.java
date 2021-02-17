package com.arc.test.random.test2;

/**
 * @author yechao
 * @since 2020/6/23 1:50 下午
 */
public class Lottery {
    /**
     * 中奖数字范围起点（通常0作为起点）
     */
    private Integer winningStartCode;
    /**
     * 当前概率计算出的中奖数字范围终点
     */
    private Integer winningEndCode;

    /**
     * 中奖的数字范围
     */
    private Integer codeScope;

    public Integer getWinningStartCode() {
        return winningStartCode;
    }

    public void setWinningStartCode(Integer winningStartCode) {
        this.winningStartCode = winningStartCode;
    }

    public Integer getWinningEndCode() {
        return winningEndCode;
    }

    public void setWinningEndCode(Integer winningEndCode) {
        this.winningEndCode = winningEndCode;
    }

    public Integer getCodeScope() {
        return codeScope;
    }

    public void setCodeScope(Integer codeScope) {
        this.codeScope = codeScope;
    }

}