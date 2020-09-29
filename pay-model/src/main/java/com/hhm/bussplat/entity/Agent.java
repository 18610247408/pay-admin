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
public class Agent implements Serializable {
    private static final long serialVersionUID = 2158539828230889440L;
    private Integer id;

    private Integer optimistic;

    private String agentNo;

    private String platformToken;

    private String phoneNo;

    private String password;

    private String realName;

    private String idCard;

    private AbleStatus ableStatus;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private String companyName;

    private String live800Code;

    private String live800Name;
    
    private String notifyUrl;
    
    private String salt;
}