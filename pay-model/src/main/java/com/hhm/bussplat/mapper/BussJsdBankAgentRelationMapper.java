package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussJsdBankAgentRelation;
import com.hhm.bussplat.enums.AbleStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BussJsdBankAgentRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BussJsdBankAgentRelation record);

    int insertSelective(BussJsdBankAgentRelation record);

    BussJsdBankAgentRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussJsdBankAgentRelation record);

    int updateByPrimaryKey(BussJsdBankAgentRelation record);

    List<BussJsdBankAgentRelation> findList(BussJsdBankAgentRelation query);

    List<BussJsdBankAgentRelation> enableBankAgentRelations(@Param("query") BussJsdBankAgentRelation query,
                                                            @Param("managerAbleStatus") AbleStatus managerAbleStatus);

    BussJsdBankAgentRelation enableBankAgentRelation(@Param("query") BussJsdBankAgentRelation query,
                                                     @Param("managerAbleStatus") AbleStatus managerAbleStatus);
}