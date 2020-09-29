package com.hhm.bussplat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hhm.bussplat.entity.BussLowerOrderLog;

public interface BussLowerOrderLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BussLowerOrderLog record);

    int insertSelective(BussLowerOrderLog record);

    BussLowerOrderLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BussLowerOrderLog record);

    int updateByPrimaryKey(BussLowerOrderLog record);
    
    BussLowerOrderLog findByOrderNo(@Param("orderNo")String orderNo,@Param("ableStatus")Integer ableStatus);
    
    
    /***
     * 补偿推送给下游的订单
     * @return
     */
    List<BussLowerOrderLog> findAllNotPushedOrder();
    
    
    
    
}