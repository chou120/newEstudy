package com.easy.zadmin.exception;

/**
 * @Author sanye
 * @Date 2023/8/26 14:27
 * @Version 1.0
 */
public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException() {
    }

    public UnauthorizedException(String ResponseResult) {
        super(ResponseResult);
    }

    public UnauthorizedException(String ResponseResult, Throwable cause) {
        super(ResponseResult, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedException(String ResponseResult, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ResponseResult, cause, enableSuppression, writableStackTrace);
    }
}
