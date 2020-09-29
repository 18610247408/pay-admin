package com.hhm.bussplat.entity;

import com.hhm.bussplat.enums.AbleStatus;
import com.hhm.bussplat.enums.SendStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shuyan.qi
 */
@Setter
@Getter
@ToString
public class BussAgentCommission implements Serializable {
    private static final long serialVersionUID = 3991679419517866739L;
    private Long id;

    private Integer optimistic;

    private String flowNo;

    private String sourceOrderNo;

    private String channelNo;

    private String costNo;

    private String agentNo;

    private BigDecimal agentIncome;

    private SendStatus agentIncomeSendStatus;

    private Date agentIncomeSendTime;

    private String agentIncomeSendRemark;

    private String userNo;

    private BigDecimal userIncome;

    private BigDecimal platformIncome;

    private AbleStatus ableStatus;

    private String remark;

    private Date createTime;

    private Date updateTime;
}