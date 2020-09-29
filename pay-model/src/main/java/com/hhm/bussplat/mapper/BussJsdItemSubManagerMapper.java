package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussJsdItemSubManager;

import java.util.List;

public interface BussJsdItemSubManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BussJsdItemSubManager record);

    int insertSelective(BussJsdItemSubManager record);

    BussJsdItemSubManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussJsdItemSubManager record);

    int updateByPrimaryKey(BussJsdItemSubManager record);

    List<BussJsdItemSubManager> findListByProductids(List<String> productids);

    int batchInsert(List<BussJsdItemSubManager> newItemSubManagers);
}