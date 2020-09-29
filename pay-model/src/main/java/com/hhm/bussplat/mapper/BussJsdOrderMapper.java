package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussJsdOrder;
import com.hhm.bussplat.enums.AbleStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BussJsdOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BussJsdOrder record);

    int insertSelective(BussJsdOrder record);

    BussJsdOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BussJsdOrder record);

    int updateByPrimaryKey(BussJsdOrder record);

    BussJsdOrder find(@Param("param1") BussJsdOrder query);

    List<BussJsdOrder> findList(@Param("param1") BussJsdOrder bussJsdOrder);

    void updateAbleStatusByIds(@Param("param1") List<Long> ids, @Param("param2") AbleStatus ableStatus);
}