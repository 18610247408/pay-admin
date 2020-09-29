package com.hhm.bussplat.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AgentMenu implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer optimistic;

    private String name;

    private String levels;

    private String status;

    private String url;

    private String resType;

    private Long parentId;

    private String label;

    private String icon;

    private String isLeaf;

    private Long displayOrder;

    private String remark;

    private String operator;

    private Date createTime;

}