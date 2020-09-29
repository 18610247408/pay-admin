package com.hhm.bussplat.agent.exception;

import com.hhm.bussplat.util.exception.BussplatException;
import com.hhm.bussplat.util.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * @author q
 * @time 2020/5/15 8:24 下午
 */
@RestControllerAdvice
@Slf4j
public class BussplatExceptionHandler {

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(BussplatException.class)
    public Result<?> handleBussplatException(BussplatException e){
        log.info(e.getMessage(),e);
        return Result.fail(e.getMessage());
    }

    /**
     * 兜底处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleThrowable(Exception e){
        log.info(e.getMessage(),e);
        return Result.error("操作失败,"+e.getMessage());
    }
}
