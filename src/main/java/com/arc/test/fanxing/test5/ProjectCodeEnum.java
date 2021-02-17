package com.arc.test.fanxing.test5;


/**
 * 内部错误码
 * 规定一定范围是一类错误，如：
 * 0001-0999 服务中的一些系统级别的异常【重大错误】
 * 1000-1999 业务中的非特定错误【运行中的普通错误】
 * 2000-2999 数据库操作级别的异常【db错误】
 * 3000-3999
 * 4000-4999 流程相关异常
 * ...
 * 9000-9998
 * <p>
 * <p>
 * ：）
 * 1）注意：在这里错误码约束有效， 下面的已有code定义看着删吧！！！
 * 2）注意： ####  看标题 ！！！
 *
 * @author 叶超
 * @since 2018/04/10
 */
public enum  ProjectCodeEnum {
    //=====================================================
    //  3个特殊状态
    //=====================================================

    FAILURE(-1, "失败"),
    SUCCESS(1, "成功"),
    UNKNOWN(0, "未知"),
    NULL(2, "NULL"),


    //=====================================================
    // 0001-0999 服务中的一些系统级别的异常   重大的错误
    //=====================================================
    /**
     * API微服务异常
     */
    MS_API_EXCEPTION(2, "API微服务异常");



    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;

    ProjectCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}

