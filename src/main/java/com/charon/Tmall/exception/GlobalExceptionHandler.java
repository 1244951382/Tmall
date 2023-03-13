package com.charon.Tmall.exception;

import com.charon.Tmall.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/2 21:32
 * @Version 1.0
 **/

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 如果抛出的异常是ServiceException则调用此方法
     * @param se
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException se) {
        return Result.error(se.getCode(), se.getMessage());
    }
}
