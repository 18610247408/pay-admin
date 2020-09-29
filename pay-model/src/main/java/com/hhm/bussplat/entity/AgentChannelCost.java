package com.hhm.bussplat.entity;

import com.hhm.bussplat.enums.AbleStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author q
 */
@Setter
@Getter
@ToString
public class AgentChannelCost {
    private Integer id;

    private Integer optimistic;

    private String costNo;

    private String channelNo;

    private String agentNo;

    private Integer agentLevel;

    private BigDecimal settleCostProportion;

    private BigDecimal saleCostProportion;

    private Date effectiveTime;

    private Date expiredTime;

    private AbleStatus ableStatus;

    private String remark;

    private String operator;

    private Date createTime;

    private Date updateTime;
}