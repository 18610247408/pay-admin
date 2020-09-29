package com.hhm.bussplat.mapper;

import com.hhm.bussplat.enums.AbleStatus;
import org.apache.ibatis.annotations.Param;

import com.hhm.bussplat.entity.BussJifenOrderResources;

public interface BussJifenOrderResourcesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BussJifenOrderResources record);

    int insertSelective(BussJifenOrderResources record);

    BussJifenOrderResources selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BussJifenOrderResources record);

    int updateByPrimaryKey(BussJifenOrderResources record);
    
    BussJifenOrderResources findPath(@Param("path")String filePath, @Param("ableStatus") AbleStatus ableStatus);
}