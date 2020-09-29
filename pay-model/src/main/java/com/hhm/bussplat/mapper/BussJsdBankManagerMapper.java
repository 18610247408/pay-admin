package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussJsdBankManager;

import java.util.List;

public interface BussJsdBankManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BussJsdBankManager record);

    int insertSelective(BussJsdBankManager record);

    BussJsdBankManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussJsdBankManager record);

    int updateByPrimaryKey(BussJsdBankManager record);

    List<BussJsdBankManager> findListOfCids(List<String> cidList);

    int batchInsert(List<BussJsdBankManager> newBankManager);
}