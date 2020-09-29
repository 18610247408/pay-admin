package com.hhm.bussplat.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AgentRoleMenu implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long menuId;

    private Long roleId;

    private String creator;

    private Date createTime;

}