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
public class BussChannel implements Serializable {
    private static final long serialVersionUID = 6031142041829565664L;
    private Integer id;

    private Integer optimistic;

    private String businessType;

    private String channelName;

    private String channelNo;

    private AbleStatus ableStatus;

    private String remark;

    private String operator;

    private Date createTime;

    private Date updateTime;
}