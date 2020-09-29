package com.hhm.bussplat.util.enums;

import com.hhm.bussplat.util.exception.BussplatException;

/**
 * 渠道枚举
 * @author q
 * @time 2020/5/26 5:11 下午
 */
public enum ChannelEnum {
    JI_SU_DUI("极速兑","jisudui"),
    ;

    private String channelName;
    private String channelNo;

    ChannelEnum(String channelName,String channelNo) {
        this.channelName = channelName;
        this.channelNo = channelNo;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public static ChannelEnum byChannelNo(String channelNo){
        if(channelNo == null || "".equals(channelNo.trim())){
            return null;
        }
        ChannelEnum[] values = values();
        for (ChannelEnum channel : values) {
            if(channel.getChannelNo().equals(channelNo)){
                return channel;
            }
        }
        return null;
    }
}
