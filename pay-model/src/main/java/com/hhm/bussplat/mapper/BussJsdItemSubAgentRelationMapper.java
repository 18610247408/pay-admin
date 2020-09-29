package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussJsdItemSubAgentRelation;
import com.hhm.bussplat.enums.AbleStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BussJsdItemSubAgentRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BussJsdItemSubAgentRelation record);

    int insertSelective(BussJsdItemSubAgentRelation record);

    BussJsdItemSubAgentRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussJsdItemSubAgentRelation record);

    int updateByPrimaryKey(BussJsdItemSubAgentRelation record);

    List<BussJsdItemSubAgentRelation> enableItemSubAgentRelations(@Param("query") BussJsdItemSubAgentRelation query,
                                                                  @Param("managerAbleStatus") AbleStatus managerAbleStatus);

    BussJsdItemSubAgentRelation enableItemSubAgentRelation(@Param("query") BussJsdItemSubAgentRelation query,
                                                           @Param("managerAbleStatus") AbleStatus managerAbleStatus);
}