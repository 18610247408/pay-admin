package com.hhm.bussplat.enums;

/**
 *
 * @author q
 * @time 2020/5/31 1:58 下午
 */
public interface IValueEnum<E> {

    /**
     * 由枚举对象转化为value值
     * @return value值
     */
    Integer toValue();

    /**
     * 由value值转换为枚举对象
     * @param value
     * @return
     */
    E parseValue(Integer value);
}
