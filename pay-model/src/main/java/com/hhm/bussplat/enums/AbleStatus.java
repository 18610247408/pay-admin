package com.hhm.bussplat.enums;

/**
 * 数据状态
 * @author q
 * @time 2020/5/31 2:44 下午
 */
public enum AbleStatus implements IValueEnum<AbleStatus>{
    /**
     * 删除
     */
    DELETE(-1),
    /**
     * 不可用
     */
    DISABLE(0),
    /**
     * 可用
     */
    ENABLE(1),
    ;
    private Integer code;

    AbleStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public Integer toValue() {
        return this.code;
    }

    @Override
    public AbleStatus parseValue(Integer value) {
        AbleStatus[] values = AbleStatus.values();
        for (AbleStatus ableStatus : values) {
            if(ableStatus.getCode().equals(value)){
                return ableStatus;
            }
        }
        return null;
    }
}
