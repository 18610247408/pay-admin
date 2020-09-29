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
public class BussCategory implements Serializable {
    private static final long serialVersionUID = -5354982486600664658L;
    private Integer id;

    private Integer optimistic;

    private String channelNo;

    private String categoryName;

    private String categoryNo;

    private String parentCategoryNo;

    private AbleStatus ableStatus;

    private String remark;

    private String operator;

    private Date createTime;

    private Date updateTime;
}