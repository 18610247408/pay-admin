package com.hhm.bussplat.mybatis.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.IntStream;

/**
 * @author q
 * @time 2020/6/14 4:16 下午
 */
public class PageHelperUtil {
    public static final String SORT_ASC = "asc";
    public static final String SORT_DESC = "desc";

    /**
     * 排序语句
     * @param filed 属性名
     * @param order 排序方向 asc / desc
     * @return
     */
    public static String sort(String filed,String order){
        if(filed == null || "".equals(filed.trim())){
            return "";
        }
        if(order == null || "".equals(filed.trim())){
            return camelToUnderscore(filed) + " " + SORT_ASC;
        }
        if(!SORT_ASC.equalsIgnoreCase(order) && !SORT_DESC.equalsIgnoreCase(order)){
            return camelToUnderscore(filed) + " " + SORT_ASC;
        }
        return camelToUnderscore(filed) + " " + order;
    }

    /**
     * 驼峰转下划线
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String camelToUnderscore(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        String[] arr = StringUtils.splitByCharacterTypeCamelCase(value);
        if (arr.length == 0) {
            return value;
        }
        StringBuilder result = new StringBuilder();
        IntStream.range(0, arr.length).forEach(i -> {
            if (i != arr.length - 1) {
                result.append(arr[i]).append("_");
            } else {
                result.append(arr[i]);
            }
        });
        return StringUtils.lowerCase(result.toString());
    }

    /**
     * 下划线转驼峰
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String underscoreToCamel(String value) {
        StringBuilder result = new StringBuilder();
        String[] arr = value.split("_");
        for (String s : arr) {
            result.append((String.valueOf(s.charAt(0))).toUpperCase()).append(s.substring(1));
        }
        return result.toString();
    }
}
