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
public class BussJsdBankManager implements Serializable {
    private static final long serialVersionUID = 959403070679900961L;
    private Integer id;

    private Integer optimistic;

    private String cid;

    private String cname;

    private AbleStatus ableStatus;

    private Date createTime;

    private Date updateTime;

    private String remark;
}