package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussJsdItemManager;

import java.util.List;

public interface BussJsdItemManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BussJsdItemManager record);

    int insertSelective(BussJsdItemManager record);

    BussJsdItemManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussJsdItemManager record);

    int updateByPrimaryKey(BussJsdItemManager record);

    List<BussJsdItemManager> findListByItemIds(List<Integer> itemids);

    int batchInsert(List<BussJsdItemManager> newItemManagers);
}