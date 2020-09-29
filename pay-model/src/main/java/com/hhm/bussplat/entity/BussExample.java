package com.hhm.bussplat.entity;

import com.hhm.bussplat.enums.AbleStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class BussExample {
    private Integer id;

    private Integer optimistic;

    private AbleStatus ableStatus;

    private BussExampleJsonContent jsonContent;

    private Date createTime;

    private String remark;
}