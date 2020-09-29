package com.hhm.bussplat.mybatis.typehandlers;

import com.hhm.bussplat.enums.IValueEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 枚举值类型处理
 * @author q
 */
@MappedJdbcTypes(value = JdbcType.INTEGER)
@Slf4j
public class ValueEnumTypeHandler<T extends IValueEnum<T>> extends BaseTypeHandler<T> {
    Class<T> clazz;
    Map<Integer,T> enumMap = new HashMap<>();

    public ValueEnumTypeHandler(Class<T> clazz) {
        if(clazz == null){
            throw new IllegalArgumentException("Enum class argument can`t be null");
        }
        this.clazz = clazz;
        T[] enumConstants = clazz.getEnumConstants();
        if(enumConstants == null){
            throw new IllegalArgumentException("Enum Elements is null");
        }
        for (T t : enumConstants){
            enumMap.put(t.toValue(),t);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T t, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,t.toValue());
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        if(rs.wasNull()){
            return null;
        }
        return getEnum(value);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        if(rs.wasNull()){
            return null;
        }
        return getEnum(value);
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int value) throws SQLException {
        return getEnum(value);
    }

    private T getEnum(Integer value){
        try {
            return enumMap.get(value);
        } catch (Exception e) {
            log.error("get enum error value=[{}],enumClass=[{}]",value,clazz.getName());
        }
        return null;
    }
}
