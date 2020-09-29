package com.hhm.bussplat.enums;

/**
 * @author q
 * @time 2020/6/1 11:34 上午
 */
public enum SyncStatus {
    /**
     * 等待同步
     */
    RE_SYNC,

    /**
     * 确认同步结果
      */
   CONFIRM,

    /**
     * 同步成功
     */
    SUCCESS,

    /**
     * 同步失败
     */
    FAIL,
}
