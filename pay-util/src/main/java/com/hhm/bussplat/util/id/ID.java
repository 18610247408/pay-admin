package com.hhm.bussplat.util.id;

/**
 * id生成器接口
 * @author q
 * @time 2020/5/16 11:20 下午
 */
public interface ID<T> {
    /**
     * 生成新的ID
     * @return
     */
    T nextId();
}
