package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussJsdItemAgentRelation;
import com.hhm.bussplat.enums.AbleStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BussJsdItemAgentRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BussJsdItemAgentRelation record);

    int insertSelective(BussJsdItemAgentRelation record);

    BussJsdItemAgentRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussJsdItemAgentRelation record);

    int updateByPrimaryKey(BussJsdItemAgentRelation record);

    List<BussJsdItemAgentRelation> enableItemAgentRelations(@Param("query") BussJsdItemAgentRelation query,
                                                            @Param("managerAbleStatus") AbleStatus managerAbleStatus);

    BussJsdItemAgentRelation enableItemAgentRelation(@Param("query") BussJsdItemAgentRelation query,
                                                     @Param("managerAbleStatus") AbleStatus managerAbleStatus);
}