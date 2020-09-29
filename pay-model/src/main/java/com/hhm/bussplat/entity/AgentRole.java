package com.hhm.bussplat.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class AgentRole implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer optimistic;

    private String name;

    private String status;

    private String remark;

    private String roleCode;

    private String roleType;

    private String allocationType;

    private String childVisible;

    /**
     * 角色所对应的资源Id
     */
    private String resourceIds;
    /**
     * 角色所对应的资源名称
     */
    private String resourceNames;
    
}