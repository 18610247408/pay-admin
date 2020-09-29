package com.hhm.bussplat.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BussJsdOrderData implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer optimistic;

    private String channelNo;

    private String orderNo;

    private String context;

    private String status;

    private Date createTime;

    private Date lastUpdateTime;

    private String dataType;

    private String countDate;

    private Integer retry;
}