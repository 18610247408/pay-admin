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
public class BussProduct implements Serializable {
    private static final long serialVersionUID = 7242920459369695017L;
    private Integer id;

    private Integer optimistic;

    private String channelNo;

    private String productName;

    private String productNo;

    private AbleStatus ableStatus;

    private String remark;

    private String operator;

    private Date createTime;

    private Date updateTime;
}