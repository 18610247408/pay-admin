package com.hhm.bussplat.agent.bean;

import lombok.Data;

import java.io.Serializable;

import com.hhm.bussplat.util.util.JsonUtil;

/**
 * LayUI 结果
 */
@Data
public class LayResult implements Serializable{
    private static final long serialVersionUID = 7228667070441310103L;
    private String code;
    private String msg;
    private Long count;
    private Object data;

    public LayResult() {

    }

    public LayResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LayResult(String code, String msg,Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public LayResult(String code, String msg, Long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public String toJson(){
        return JsonUtil.toJson(this);
    }

    public static LayResult success(){
        return  new LayResult(IReturnBean.SUCCESS,IReturnBean.SUCCESS_DESC,null,null);
    }

    public static LayResult success(String msg){
        return  new LayResult(IReturnBean.SUCCESS,msg,null,null);
    }

    public static LayResult success(Long count, Object data){
        return  new LayResult(IReturnBean.SUCCESS,IReturnBean.SUCCESS_DESC,count,data);
    }

    public static LayResult fail(){
        return  new LayResult(IReturnBean.FAIL,IReturnBean.FAIL_DESC,null,null);
    }

    public static LayResult fail(String msg){
        return  new LayResult(IReturnBean.FAIL,msg,null,null);
    }

    public static LayResult fail(Long count, Object data){
        return  new LayResult(IReturnBean.FAIL,IReturnBean.FAIL_DESC,count,data);
    }

    public static LayResult error(){
        return  new LayResult(IReturnBean.ERROR,IReturnBean.ERROR_DESC,null,null);
    }

    public static LayResult error(String msg){
        return  new LayResult(IReturnBean.ERROR,msg,null,null);
    }

    public static LayResult error(Long count, Object data){
        return  new LayResult(IReturnBean.ERROR,IReturnBean.ERROR_DESC,count,data);
    }
}
