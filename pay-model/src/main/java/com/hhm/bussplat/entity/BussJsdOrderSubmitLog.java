package com.hhm.bussplat.entity;

import com.hhm.bussplat.enums.AbleStatus;
import com.hhm.bussplat.enums.HandleStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author q
 */
@Setter
@Getter
@ToString
public class BussJsdOrderSubmitLog implements Serializable {
    private static final long serialVersionUID = 6678601664018826353L;
    private Long id;

    private Integer optimistic;

    private String agentNo;

    private String saleNo;

    private String bankId;

    private String itemId;

    private String productId;

    private String params;

    private Integer resultStatus;

    private String result;

    private HandleStatus handleJifenOrdeStatus;

    private Date handleJifenOrdeTime;

    private String jifenOrderBatchNo;

    private AbleStatus ableStatus;

    private String remark;

    private Date createTime;

    private Date updateTime;
}