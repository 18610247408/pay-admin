package com.hhm.bussplat.agent.bean.resultBean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;



@Data
public class AgentInfoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String agentNo;
	private String phoneNo;
	private String realName;
	private String companyName;
	private String idCard;
	private Date createTime;
	
	private Double balance;
	private Double transitBalance;
	private Double freezeBalance;
	

}
