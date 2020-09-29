package com.hhm.bussplat.agent.bean;

import java.io.Serializable;

public class IReturnBean<T> implements Serializable {
    private static final long serialVersionUID = -539534353423L;
    private String isAllowed = "0";
    /**成功*/
    public static final String SUCCESS = "0000";
    public static final String SUCCESS_DESC = "操作成功";

    /**异常*/
    public static final String ERROR = "0001";
    public static final String ERROR_DESC = "服务器维护中，请稍后重试";

    /**失败*/
    public static final String FAIL = "0002";
    public static final String FAIL_DESC = "操作失败";

    /**
	 * 前置调core项目dubbo服务异常
	 */
	public static final String DUBOO_ERROR_CODE = "0000001";

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回消息
     */
    private String msg;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 操作对象
     */
    private T data;

    /** 无参构造方法 */
    public IReturnBean() {
        super();
    }

    /**
     * 构造方法
     * @param code 返回码
     * @param msg 返回消息
     */
    public IReturnBean(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }




    /**
     * 构造方法
     * @param code 返回码
     * @param msg 返回消息
     * @param data 操作对象
     */
    public IReturnBean(String code, String msg,T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 构造方法
     * @param data 操作对象
     */
    public IReturnBean(T data) {
        super();
        this.code = IReturnBean.SUCCESS;
        this.msg = "";
        this.data = data;
    }

    public static <T> IReturnBean success(T data){
        return new IReturnBean<T>(IReturnBean.SUCCESS,IReturnBean.SUCCESS_DESC,data);
    }
    public static <T> IReturnBean fail(T data){
        return new IReturnBean<T>(IReturnBean.FAIL,IReturnBean.FAIL_DESC,data);
    }
    public static <T> IReturnBean error(T data){
        return new IReturnBean<T>(IReturnBean.ERROR,IReturnBean.ERROR_DESC,data);
    }

    public String getCode() {
        if(code == null){
            return "";
        }
        if("".equals(code.trim())){
            return "";
        }

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        if(msg == null){
            return "";
        }
        if("".equals(msg.trim())){
            return "";
        }

        int length = msg.length();
        if(length > 512){
            length=512;
        }
        return msg.substring(0,length);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getIsAllowed() {
        return isAllowed;
    }

    public void setIsAllowed(String isAllowed) {
        this.isAllowed = isAllowed;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReturnBean [code=");
        builder.append(code);
        builder.append(", isAllowed=");
        builder.append(isAllowed);
        builder.append(", msg=");
        builder.append(msg);
        builder.append(", data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }
}
