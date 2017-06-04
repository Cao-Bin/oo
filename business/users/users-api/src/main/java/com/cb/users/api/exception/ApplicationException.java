package com.cb.users.api.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

public abstract class ApplicationException extends HystrixBadRequestException {
    private String code;

    public ApplicationException(String code, String msg) {
        super(msg);
        this.code = code;
    }


    public String getCode() {
        return code;
    }

}
