package com.hhm.bussplat.entity;

import com.hhm.bussplat.enums.AbleStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shuyan.qi
 */
@Setter
@Getter
@ToString
public class BussJsdItemAgentRelation implements Serializable {
    private static final long serialVersionUID = 852954938160595418L;
    private Integer id;

    private Integer optimistic;

    private String agentNo;

    private Integer itemId;

    private AbleStatus ableStatus;

    private Date createTime;

    private Date updateTime;

    private String remark;
}