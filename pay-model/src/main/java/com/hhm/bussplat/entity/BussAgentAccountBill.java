package com.hhm.bussplat.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BussAgentAccountBill implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer optimistic;

    private String accountNo;

    private String agentNo;

    private String sourceCommFlowNo;

    private Double amount;

    private String operateType;

    private String status;

    private String failReason;

    private Date countDate;

    private Date createTime;

    private Date updateTime;

    private String accDesc;

}