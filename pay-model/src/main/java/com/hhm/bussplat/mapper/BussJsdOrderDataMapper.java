package com.hhm.bussplat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hhm.bussplat.entity.BussJsdOrderData;

public interface BussJsdOrderDataMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(BussJsdOrderData record);

    int insertSelective(BussJsdOrderData record);

    BussJsdOrderData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BussJsdOrderData record);

    int updateByPrimaryKey(BussJsdOrderData record);
    
    BussJsdOrderData findByOrderNo(@Param("channelNo")String channelNo,@Param("orderNo")String orderNo);
    
    /***
     * 补偿未处理的上游订单
     * @return
     */
    List<BussJsdOrderData> findAllNotPushedOrder(@Param("channelNo")String channelNo);
    
    

}