package com.hhm.bussplat.util.exception;

/**
 * 自定义运行时异常
 * @author q
 * @time 2020/5/15 8:19 下午
 */
public class BussplatException extends RuntimeException{
    private static final long serialVersionUID = -4974085716414533234L;
    
    private String code;		//错误码
    private String message;		//错误描述
    
    public static final String REPEAT_NOTIFY_CODE = "0007";
    public static final String REPEAT_NOTIFY_MESSAGE = "重复提交";
    

    public BussplatException(String code,String message) {
    	this.code = code;
    	this.message = message;
    }
    

    public BussplatException(String message) {
        this.message = message;
    }

    public BussplatException(Throwable cause) {
        super(cause);
    }

    public BussplatException(String message, Throwable cause) {
        super(message, cause);
    }


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
