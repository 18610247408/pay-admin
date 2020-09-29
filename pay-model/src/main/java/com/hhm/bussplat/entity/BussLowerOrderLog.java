package com.hhm.bussplat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.hhm.bussplat.enums.AbleStatus;

import lombok.Data;

@Data
public class BussLowerOrderLog implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer optimistic;

    private Integer type;

    private String agentNo;

    private String userNo;

    private String orderNo;

    private String orgName;

    private BigDecimal agentAmount;

    private BigDecimal agentPrice;

    private BigDecimal userAmount;

    private BigDecimal userPrice;

    private BigDecimal point;

    private Integer status;

    private String checkDate;

    private String baodanDate;

    private String inDate;

    private Date createTime;

    private Date updateTime;

    private String syncStatus;

    private Integer retry;

    private AbleStatus ableStatus;
    
    
    private String bankName;
    
    private String itemName;
    
    
}