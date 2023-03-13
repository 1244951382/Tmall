package com.charon.Tmall.exception;

import lombok.Getter;

/**
 * @ClassName ServiceException
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/2 21:02
 * @Version 1.0
 **/

@Getter
public class ServiceException extends RuntimeException{

    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
