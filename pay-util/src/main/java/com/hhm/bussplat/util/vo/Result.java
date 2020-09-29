package com.hhm.bussplat.util.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.io.Serializable;

/** 视图层通用结果Bean
 * @author q
 * @time 2020/5/15 5:51 下午
 */
@Setter
@Getter
@ToString
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -5693054347866463804L;
    private static volatile ObjectMapper mapper = new ObjectMapper();

    private String code;
    private String msg;
    private T data;

    public static final String SUCCESS_CODE = "0000";
    public static final String SUCCESS_MSG = "success";

    public static final String FAIL_CODE = "0001";
    public static final String FAIL_MSG = "请求失败";

    public static final String ERROR_CODE = "0002";
    public static final String ERROR_MSG = "服务器异常，请联系我们";


    public Result() {
    }

    public Result(String code) {
        this.code = code;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(){
        return new Result(SUCCESS_CODE,SUCCESS_MSG);
    }

    public static <T> Result<T> success(T t){
        return new Result<T>(SUCCESS_CODE,SUCCESS_MSG,t);
    }

    public static <T> Result<T> success(T t,String msg){
        return new Result<T>(SUCCESS_CODE,msg == null || "".equals(msg.trim()) ? SUCCESS_MSG : msg,t);
    }

    public static Result fail(){
        return new Result(FAIL_CODE,FAIL_MSG);
    }

    public static Result fail(String msg){
        return new Result(FAIL_CODE,msg == null || "".equals(msg.trim()) ? FAIL_MSG : msg);
    }

    public static Result error(){
        return new Result(ERROR_CODE,ERROR_MSG);
    }

    public static Result error(String msg){
        return new Result(ERROR_CODE,msg == null || "".equals(msg.trim()) ? ERROR_MSG : msg);
    }

    public  String toJsonString(){
        try {
            return mapper.writeValueAsString(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
