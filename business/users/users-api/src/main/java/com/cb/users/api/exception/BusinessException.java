package com.cb.users.api.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

public abstract class BusinessException extends HystrixBadRequestException {
    private String code;

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
    }


    public String getCode() {
        return code;
    }

}
