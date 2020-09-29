package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussJsdOrderSubmitLog;

import java.util.List;

public interface BussJsdOrderSubmitLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BussJsdOrderSubmitLog record);

    int insertSelective(BussJsdOrderSubmitLog record);

    BussJsdOrderSubmitLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BussJsdOrderSubmitLog record);

    int updateByPrimaryKey(BussJsdOrderSubmitLog record);

    List<BussJsdOrderSubmitLog> findList(BussJsdOrderSubmitLog submitLog);
}