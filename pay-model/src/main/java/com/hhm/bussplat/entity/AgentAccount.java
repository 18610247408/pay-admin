package com.hhm.bussplat.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AgentAccount implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer optimistic;

    private String agentNo;

    private String roleType;

    private String accountNo;

    private String type;

    private Double balance;

    private Double transitBalance;

    private Double freezeBalance;

    private String status;

    private Date openTime;

    private Date updateTime;

}