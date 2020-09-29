package com.hhm.bussplat.enums;

/**
 * 发放状态
 * @author q
 * @time 2020/5/31 3:43 下午
 */
public enum SendStatus{
    /**
     * 不发放
     */
    NO_SEND,

    /**
     * 待发放
     */
    RE_SEND,

    /**
     * 发放成功
     */
    SUCCESS,

    /**
     * 发放失败
     */
    FAIL,

    /**
     * 取消发放
     */
    CANCEL;
}
