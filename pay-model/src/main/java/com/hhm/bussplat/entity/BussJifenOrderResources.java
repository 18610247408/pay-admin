package com.hhm.bussplat.entity;

import com.hhm.bussplat.enums.AbleStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author q
 */
@Setter
@Getter
@ToString
public class BussJifenOrderResources implements Serializable {
    private static final long serialVersionUID = 1474131388674514849L;
    private Long id;

    private Integer optimistic;

    private String channelNo;

    private String orderNo;

    private String path;

    private AbleStatus ableStatus;

    private String remark;

    private Date createTime;

    private Date updateTime;
}