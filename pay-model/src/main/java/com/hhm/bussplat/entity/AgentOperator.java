package com.hhm.bussplat.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AgentOperator implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer optimistic;

    private String username;

    private String password;

    private String status;

    private Integer maxErrorTimes;

    private Date lastLoginErrTime;

    private Integer modifyPasswdCycle;

    private Date passwdModifyTime;

    private Date allowBeginTime;

    private Date allowEndTime;

    private String operatorType;

    private String realname;

    private String createOperator;

    private String reloginFlag;

    private Date passwdExpireTime;

    private Date passwdEffectTime;

    private String orgCode;

    private Date createTime;

    private Date lastUpdateTime;
    
    private String agentNo;

}