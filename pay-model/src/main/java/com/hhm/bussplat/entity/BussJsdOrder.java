package com.hhm.bussplat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhm.bussplat.enums.AbleStatus;
import com.hhm.bussplat.enums.SyncStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author q
 */
@Setter
@Getter
@ToString
public class BussJsdOrder implements Serializable {
    private static final long serialVersionUID = -3566921342575527115L;
    private Long id;

    private Integer optimistic;

    private String agentNo;

    private String userNo;

    private Integer baodanType;

    private Integer bankId;

    private String bankName;

    private Integer itemId;

    private String itemName;

    private Integer productId;

    private String productName;

    private String msg;

    private String password;

    private String picUrl;

    private String validDate;

    private String jsdOrderNo;

    private Integer auditStatus;

    private Date auditTime;

    private BigDecimal duiPrice;

    private BigDecimal point;

    private String orderNo;

    private String batchNo;

    private String costNo;

    private BigDecimal platformIncome;

    private BigDecimal agentIncome;

    private BigDecimal userIncome;

    private AbleStatus ableStatus;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private transient String createTimeFrom;
    private transient String createTimeTo;

    private transient String auditTimeFrom;
    private transient String auditTimeTo;

    private transient String ids;
}